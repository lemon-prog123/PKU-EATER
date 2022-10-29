package com.example.activitytest.data

data class BaseData(var errCode:Int,var errMsg:String)
data class PostData(  var  status: String,var  data:BaseData)
