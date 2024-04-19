package com.example.splitapp.DataLayer.DataModel

import java.util.UUID





data class GroupModel(
    var id :String?,
    var name: String?,
    var description: String?,
    var owner:String?,
    var member: List<String>?,

){
    constructor() : this(null,null, null, null,null)

}

data class oweMap (
    var allMap:Map<String , Float>?
) {
    constructor():this(null)
}



data class Friend (
    val id:String?,
    val username:String?,
    val name: String?,
)

data class GroupLog (
    val id : String,
    val owner:String,
    val name : String,
    val total : Float,
    val description: String,
    val dateCreate: String,
    val involved: Map<String , Float>
)




