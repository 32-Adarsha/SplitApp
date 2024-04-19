package com.example.splitapp.DataLayer.DataModel

import java.util.UUID


data class FriendId(
    val id:String?,
    val owes:Float?
)

data class GroupId(
    val id:String?
)

data class Usermodel (
    var id: String?,
    var email: String?,
    var username: String?,
    var first_name: String?,
    var last_name: String?
) {
    constructor() : this(null,null, null, null, null)
}
