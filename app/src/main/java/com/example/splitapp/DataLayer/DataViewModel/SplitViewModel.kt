package com.example.splitapp.DataLayer.DataViewModel

import android.util.Log
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitapp.DataLayer.DataModel.Friend
import com.example.splitapp.DataLayer.DataModel.GroupLog
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.Repository.DisplayRepository
import com.example.splitapp.DataLayer.Repository.GroupRepository
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SplitViewModel @Inject constructor(
    private val displayRepository: DisplayRepository,
    private val groupRepository: GroupRepository,
):ViewModel() {
    val allGroup= displayRepository.allGroup
    val allOwed = displayRepository.allOwed
    val allGroupLog = displayRepository.allLog
    val friend  = displayRepository.allFriends
    val _count = MutableStateFlow<Int>(0)
    val count: StateFlow<Int> = _count.asStateFlow()
    val _viewGroupDetail = MutableStateFlow<Int>(0)
    val viewGroupDetail = _viewGroupDetail.asStateFlow()



    init {
        viewModelScope.launch {
            displayRepository.fetchAllFirend()
        }
    }
    suspend fun addGroup(ownerId:String , name:String , description:String, member:List<String>){
        viewModelScope.launch {
            groupRepository.createGroup(ownerId , member , name , description)

        }
    }

    suspend fun addGroupLog(groupId:String  , ownerId: String , involved:Map<String , Float> , name: String, total:Float , description: String , isSettled:Boolean){
        viewModelScope.launch {
            groupRepository.createGroupLog(groupId ,ownerId , involved , name, total , description , isSettled)
        }
    }
    suspend fun fetchUserGroup(){
        displayRepository.fetchAllGroup()
    }

    fun getGroupMember(id:String): List<String>? {
        return allGroup.value[id]?.member
    }

    fun getUserFromId(id:String):Usermodel?{
        return friend.value[id]?:null
    }

    fun getGroupLog(id:String):List<GroupLog>?{
        return allGroupLog.value[id]?:null
    }

    fun getGroupOwed(id:String):Map<String , Float>? {
        return allOwed.value[id]
    }

    fun getGroupFromID(id:String):GroupModel? {
        return allGroup.value[id]?:null
    }


    fun getTotalOwedInGroup(id:String):Float {
        val grouplog = allOwed.value[id]?.toList()
        var totalOwed:Float = 0f
        if (grouplog != null){
            for ((key , value) in grouplog){
                totalOwed += value
            }
        }
        return  totalOwed

    }

    fun getOverallOwed():Float {
        var totalOwed = 0f
        for ((key , value ) in allOwed.value) {
            totalOwed += getTotalOwedInGroup(key)
        }
        return totalOwed
    }

    fun getAllFriend():List<String>? {
        var memberList:MutableList<String> = mutableListOf()
        for((key , value ) in friend.value){
            memberList.add(key)
        }
        return  memberList.toList()
    }


}