package com.example.splitapp.DataLayer.IRepository

import com.example.splitapp.DataLayer.DataModel.Usermodel

interface IGroupRepository {
    suspend fun createGroup(ownerId:String , member:List<Usermodel>? , name:String , description:String )
    suspend fun createGroupLog(groupId: String, ownerId: String, involved:Map<String, Float> , name: String ,total:Float, description: String , isSetteld:Boolean)
}