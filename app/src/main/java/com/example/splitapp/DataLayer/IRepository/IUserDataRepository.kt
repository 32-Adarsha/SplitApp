package com.example.splitapp.DataLayer.IRepository

import com.example.splitapp.DataLayer.DataModel.FriendId
import com.example.splitapp.DataLayer.DataModel.GroupId
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.google.firebase.database.DatabaseReference

interface IUserDataRepository {

    var userRef:DatabaseReference?
    suspend fun addSelf(id:String , usermodel: Usermodel) : Boolean
    suspend fun addFriend(id:String?):Boolean
    suspend fun addGroup (id:String?):Boolean

}


//data class FriendId(
//    val id:String?
//)
//
//data class GroupId(
//    val id:String?
//)
//
//data class Usermodel (
//    var email:String?,
//    var username:String?,
//    var first_name:String?,
//    var last_name:String?,
//    var mutualFriends: List<FriendId>?,
//    var groups:List<GroupId>?
//)