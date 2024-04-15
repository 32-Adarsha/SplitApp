package com.example.splitapp.DataLayer.DataViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitapp.DataLayer.DataModel.Friend
import com.example.splitapp.DataLayer.DataModel.GroupLog
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class SplitViewModel:ViewModel() {
    private var _allGroup = MutableStateFlow<List<GroupModel>>(emptyList())
    val allGroup: StateFlow<List<GroupModel>> = _allGroup.asStateFlow()
    var _friend = MutableStateFlow<MutableList<Friend>>(mutableListOf())
    val friend :StateFlow<MutableList<Friend>> = _friend.asStateFlow()
    val _count = MutableStateFlow<Int>(0)
    val count: StateFlow<Int> = _count.asStateFlow()
    val _viewGroupDetail = MutableStateFlow<Int>(0)
    val viewGroupDetail = _viewGroupDetail.asStateFlow()



    init {
        var listOfriend:MutableList<Friend> = mutableListOf (
            Friend("Adarsha" , "32" ),
            Friend("Kiran" , "Kira" ),
            Friend("Khadka" , "akdahk" ),
            Friend("Abisha" , "9990" ),
            Friend("Ghimira" , "45ui" )
                )
        _friend.value = listOfriend
    }
    fun addGroup(newGroup:GroupModel){
        viewModelScope.launch {
       try {
           val gList: List<GroupModel> = _allGroup.value.toMutableList() + newGroup
           _allGroup.value = gList
           val c= _count.value+1
           _count.value = c
           //


       }catch (e:Exception){
           Log.e("Test" , "${e.message}")
       }

        }
    }

    fun logCount(){
        Log.e("log" , "${_count.value}")
    }

    fun selectedGroup(id:Int){
        val newId = id
        _viewGroupDetail.value = newId
    }

    fun postTransaction(id:Int , llog:GroupLog){
        val test = _allGroup.value
        test[id].log.add(llog)
        _allGroup.value = test
    }





}