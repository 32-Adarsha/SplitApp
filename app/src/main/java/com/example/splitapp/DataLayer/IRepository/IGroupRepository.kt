package com.example.splitapp.DataLayer.IRepository

interface IGroupRepository {
    suspend fun createGroup(ownerId:String , member:List<String> , name:String , description:String)
    suspend fun createGroupLog(groupId: String, ownerId: String, involved:Map<String, Float> , name: String ,total:Float, description: String)
}