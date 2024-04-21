package com.example.splitapp.DataLayer.Repository

import com.example.splitapp.DataLayer.DataModel.GroupInfo
import com.example.splitapp.DataLayer.DataModel.GroupLog
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.example.splitapp.DataLayer.IRepository.IGroupRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

//    private val _allGroup = MutableStateFlow<List<>>(emptyList())
//    val allGroup = _allGroup.asStateFlow()
    override suspend fun createGroup(ownerId:String , members:List<String>,name:String , description:String ){
        val mainRef = database.reference

        val newGroupId = UUID.randomUUID().toString()
        val newGroup = GroupModel(newGroupId , name , description ,ownerId , members)
        var mapOfowes:MutableMap<String,Float> = mutableMapOf()
        for (member in members){
            mapOfowes[member] = 0f
        }
        for(member in members){
            mainRef.child("Users/$member").child("groups").child(newGroupId).setValue(mapOfowes)
        }

        mainRef.child("Groups").child("$newGroupId").setValue(newGroup)

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
            val fromRef = mainRef.child("Users/${ownerId}/groups/${groupId}/${key}")
            val ToRef = mainRef.child("Users/${key}/groups/${groupId}/${ownerId}")
            if (isSettled) {
                LogAddHelper(fromRef, -value)
                LogAddHelper(ToRef, value)
            } else {
                LogAddHelper(fromRef, value)
                LogAddHelper(ToRef, -value)
            }
        }
        var dateNow = LocalDateTime.now().toString()
        val CreateGroupLog = GroupLog (newLogId , ownerId , name , total, description , dateNow , involved)
        mainRef.child("Logs").child("${groupId}").child(newLogId).setValue(CreateGroupLog)

    }


}