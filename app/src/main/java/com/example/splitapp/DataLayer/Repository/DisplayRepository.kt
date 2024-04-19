package com.example.splitapp.DataLayer.Repository

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.DataModel.oweMap
import com.example.splitapp.DataLayer.IRepository.IDisplayRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    override suspend fun fetchAllFirend(){
        val usersRef = database.reference.child("Users")
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                var tempMap:MutableMap<String , Usermodel> = mutableMapOf()
                for (data in snapshot.children) {

                    var userModel = data.getValue(Usermodel::class.java)
                    if (userModel != null) {
                        var key = userModel.id
                        tempMap[key!!] = userModel
                    }

                }
                _allFriends.value = tempMap

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    suspend fun fetchAllGroup(){
        val usersRef = database.reference.child("Users/${authRepository.thisUser.value!!.uid}/groups")
        usersRef.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                for (groupSnapshot in snapshot.children) {

                    val groupId = groupSnapshot.key ?: continue
                    var oMap =  groupSnapshot.value as? Map<String , Float>?:continue

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

                }


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }




}
