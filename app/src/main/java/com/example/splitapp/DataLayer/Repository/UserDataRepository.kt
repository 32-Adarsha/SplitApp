package com.example.splitapp.DataLayer.Repository

import android.os.UserManager
import com.example.splitapp.DataLayer.DataModel.FriendId
import com.example.splitapp.DataLayer.DataModel.GroupId
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.IRepository.IUserDataRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val database:FirebaseDatabase
):IUserDataRepository{

    override var userRef: DatabaseReference? = null
        get() = userRef
        set(value) {field=value}

    override suspend fun addFriend(id: String?): Boolean {
        try{
        userRef?.child("mutualFriends")?.setValue(id)
            return  true
        } catch (e:Exception){
            return false
        }

    }

    override suspend fun addGroup(id: String?): Boolean {
        try {
            userRef?.child("groups")?.setValue(id)
            return true
        }catch (e:Exception){
            return false
        }
    }

    override suspend fun addSelf(
        id: String,
        usermodel: Usermodel
    ): Boolean {
        try {
            var UsersRef = database.reference.child("Users")
            UsersRef.child(id).setValue(usermodel)
            userRef = UsersRef.child(id)
            return true
        } catch (e:Exception){
            return false
        }


    }



}