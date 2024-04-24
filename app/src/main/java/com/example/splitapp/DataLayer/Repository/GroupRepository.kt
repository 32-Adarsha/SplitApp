package com.example.splitapp.DataLayer.Repository

import com.example.splitapp.DataLayer.DataModel.GroupId
import com.example.splitapp.DataLayer.DataModel.GroupInfo
import com.example.splitapp.DataLayer.DataModel.GroupLog
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.IRepository.IGroupRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GroupRepository @Inject constructor(
    private val database: FirebaseDatabase,
    private val authRepository: AuthRepository
):IGroupRepository {


    override suspend fun createGroup(ownerId:String , members:List<Usermodel>?,name:String , description:String ){
        val mainRef = database.reference

        val newGroupId = UUID.randomUUID().toString()
        val newGroup = GroupModel(newGroupId , name , description ,ownerId , listOf(ownerId))

    if (members != null) {
        for(member in members){
            var newInviteRef  = mainRef.child("Users/${member.id}").child("groupInvitation")
            newInviteRef.child(newGroupId).setValue(true)
        }
    }

    mainRef.child("Groups").child("$newGroupId").setValue(newGroup)

    mainRef.child("Users/$ownerId").child("Groups").child(newGroupId).child("$ownerId").setValue(0)

    }

    fun LogAddHelper(valueRef:DatabaseReference , amount:Float) {
        valueRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val currentValue = dataSnapshot.getValue(Float::class.java) ?: 0f
                val newValue = currentValue + amount

                valueRef.setValue(newValue)
                    .addOnSuccessListener {

                    }
                    .addOnFailureListener {

                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }


    override suspend fun createGroupLog(
        groupId: String,
        ownerId: String,
        involved: Map<String, Float>,
        name: String,
        total:Float,
        description: String,
        isSettled: Boolean
    ) {
        val mainRef = database.reference
        val newLogId = UUID.randomUUID().toString()
        involved.forEach { (key, value) ->
            val fromRef = mainRef.child("Users/${ownerId}/Groups/${groupId}/${key}")
            val ToRef = mainRef.child("Users/${key}/Groups/${groupId}/${ownerId}")
            if (isSettled) {
                LogAddHelper(fromRef, value)
                LogAddHelper(ToRef, -value)
            } else {
                LogAddHelper(fromRef, value)
                LogAddHelper(ToRef, -value)
            }
        }
        var dateNow = LocalDateTime.now().toString()
        val CreateGroupLog = GroupLog (newLogId , ownerId , name , total, description , dateNow , involved)
        mainRef.child("Logs").child("${groupId}").child(newLogId).setValue(CreateGroupLog)

    }


    suspend fun acceptGroupRequest(userId:String , groupId: String){
        var groupRef = database.reference.child("Groups/$groupId/member")
        groupRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    var eachMemberId = data.getValue(String::class.java)
                    database.reference.child("Users/${eachMemberId}/Groups/${groupId}").child("$userId").setValue(0f)
                    database.reference.child("Users/${userId}/Groups/${groupId}").child("$eachMemberId").setValue(0f)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        var memberref = database.reference.child("Groups/$groupId/member")
        memberref.addListenerForSingleValueEvent(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var listOfMember = snapshot.value as? List<String>?: null
                var newList = listOfMember?.toMutableList()
                newList?.add(userId)
                memberref.setValue(newList?.toList())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        database.reference.child("Users/$userId/groupInvitation").child("$groupId").removeValue()


    }




}