package com.example.splitapp.DataLayer.DataModel


data class AllGroup(
    val groupModel:MutableList<GroupModel> = ArrayList(),
)
data class GroupModel(
    var name: String,
    var description: String,
    var member: MutableList<Friend> = mutableListOf(),
    var owes: Float = 0f,
    var log: MutableList<GroupLog> = mutableListOf()
)

data class Friend (
    val name: String,
    val nickname: String,
)





data class GroupLog (
    val id : Int,
    val name : String,
    val description: String,
    val involved: List<Friend>
)

