package com.example.splitapp.DataLayer.Repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.splitapp.DataLayer.DataModel.GroupLog
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.IRepository.IDisplayRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DisplayRepository @Inject constructor(
    private val database: FirebaseDatabase,
    private val authRepository: AuthRepository
):IDisplayRepository {

    private val _allFriends = MutableStateFlow<Map<String , Usermodel>>(emptyMap())
    val allFriends = _allFriends.asStateFlow()
    private val _allGroup = MutableStateFlow<Map<String , GroupModel>>(emptyMap())
    val allGroup = _allGroup.asStateFlow()
    private val _allOwed = MutableStateFlow<Map<String , Map<String, Float>>>(emptyMap())
    val allOwed = _allOwed.asStateFlow()
    private val _allLog = MutableStateFlow<Map<String , List<GroupLog>?>>(emptyMap())
    val allLog = _allLog.asStateFlow()
    private val _allGroupRequest = MutableStateFlow<Map<String , GroupModel>>(emptyMap())
    val allGroupRequest = _allGroupRequest.asStateFlow()
    private val _allFriendsNew = MutableStateFlow<Map<String , Usermodel?>>(emptyMap())
    val allFriendsNew = _allFriendsNew.asStateFlow()


    override suspend fun fetchAllFirend(id:String){
        val usersRef = database.reference.child("Users/$id/Friends")
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){

                var tempMap:MutableMap<String , Usermodel> = mutableMapOf()
                for (data in snapshot.children) {
                    var friendId = data.getValue(String::class.java)
                    var eachUserRef = database.reference.child("Users/$friendId")
                    eachUserRef.addValueEventListener(object :ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                           var eachUserModel = snapshot.getValue(Usermodel::class.java)
                            tempMap[eachUserModel?.id!!] = eachUserModel
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.e("Error" , "Friend Cant be extracted")
                        }

                    })

                }
                _allFriends.value = tempMap

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    suspend fun fetchAllGroup(){
        val usersRef = database.reference.child("Users/${authRepository.thisUser.value!!.uid}/Groups")
        usersRef.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                for (groupSnapshot in snapshot.children) {

                    val groupId = groupSnapshot.key ?: continue
                    var oMap =  groupSnapshot.value as? Map<String , Float>?:continue

                    for ((key , value) in oMap){
                        var indRef = database.reference.child("Users/$key")
                        indRef.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                var indUserModel = snapshot.getValue(Usermodel::class.java)
                                var newALlFriend = _allFriendsNew.value.toMutableMap()
                                newALlFriend[key] = indUserModel
                                _allFriendsNew.value = newALlFriend.toMap()
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        })
                    }


                    var currentOlist = _allOwed.value.toMutableMap()
                    currentOlist[groupId] = oMap
                    _allOwed.value = currentOlist

                    val groupRef = database.reference.child("Groups/$groupId")
                    groupRef.addValueEventListener(object:ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val group = snapshot.getValue(GroupModel::class.java)
                            if (group != null) {
                                var currentList = _allGroup.value.toMutableMap()
                                currentList[groupId] = group
                                _allGroup.value = currentList.toMap()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.e("Show" , "Cant Add Data")
                        }
                    })

                    var groupLogRef = database.reference.child("Logs/$groupId")
                    groupLogRef.addValueEventListener( object :ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            var groupLogList = mutableListOf<GroupLog>()
                            snapshot.children.forEach { dataSnapshot ->
                                var groupLog= dataSnapshot.getValue(GroupLog::class.java)
                                groupLogList.add(groupLog!!)
                            }
                            var currentLog = _allLog.value.toMutableMap()
                            currentLog[groupId] = groupLogList
                            _allLog.value = currentLog

                        }
                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })


                }


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }



    suspend fun fetchTheUserFromEmail(email:String): Usermodel? {
        var newEmail = email.replace("." , "")
        val userId = database.reference.child("AllUser").child(newEmail).get().await().value?.toString()
        return if (userId != null) {
            database.reference.child("Users").child(userId).get().await().getValue(Usermodel::class.java)
        } else {
            Log.e("Problem" , "Problem")
            null
        }
    }




    suspend fun getGroupRequest(thisUser: String){
        var groupRef = database.reference.child("Users/$thisUser").child("groupInvitation")

        groupRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    val groupId = data.key ?: continue
                    getGroup(groupId)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    @SuppressLint("SuspiciousIndentation")
    fun getGroup(groupId:String){
      var gRef = database.reference.child("Groups").child("$groupId")
        gRef.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var groupModel = snapshot.getValue(GroupModel::class.java)
                var tempHolder = _allGroupRequest.value.toMutableMap()
                if (groupModel != null) {
                    tempHolder[groupId] = groupModel
                }
                _allGroupRequest.value = tempHolder.toMap()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun deleteGroupRequest(userId:String , groupId: String){
       val temp = _allGroupRequest.value.toMutableMap()
        temp.remove(groupId)
        _allGroupRequest.value = temp.toMap()
    }


}
