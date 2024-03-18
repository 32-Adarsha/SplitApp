package com.example.splitapp.data

data class friend(val name:String ,val nickname:String ,val owe:Float)
data class log(val name:String , val total:Float , val involved:List<friend>)
data class Group(val name:String , val discription:String,var totalOwed:Float , val member : List<friend> , val log:List<log>)


