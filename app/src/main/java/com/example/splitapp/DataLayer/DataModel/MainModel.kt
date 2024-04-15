package com.example.splitapp.DataLayer.DataModel

import java.util.UUID



data class GroupInfo(
    var description: String?,
    var name: String?,
    var creator:String?,
)

data class LogInfo (
    var name: String?,
    var description: String?,
    var type:String?,
    var creator:String?
)

data class LogInvolved (
    var id : UUID?,
    var owes:Float?,
)

data class Log (
    var info:LogInfo?,
    var involved:LogInvolved?
)



data class Groups(
    var info:GroupInfo?,
    var logs:List<Log>?,
    var member:List<LogInvolved>?
 )
