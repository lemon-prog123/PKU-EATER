package com.example.activitytest.data

import android.os.FileUtils
import com.google.gson.Gson
import java.io.File
import java.io.FileReader


data class User(
    var  username: String,
    var password: String
)

fun main()
{
    //val file = File("a.json")
    //file.createNewFile()
    //val content = File("a.json").readText()
    val content="{\"status\":\"fail\",\"data\":{\"errCode\":10001,\"errMsg\":\"User Name has been used.\"}}"
    val newUser=Gson().fromJson(content, PostData::class.java)
    println(newUser.status)
    //val user = User(username="阿四", password="123456")
    //val json = Gson().toJson(user)
}