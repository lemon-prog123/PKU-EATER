package com.example.activitytest.data

import android.util.Log
import com.example.activitytest.data.model.LoggedInUser
import com.example.activitytest.simplePostUseFrom
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            Log.d("username",username)
            Log.d("password",password)
            val globalFile=Data
            globalFile.setUserName(username)
            globalFile.setPassword(password)
            val map = mapOf("name" to username,"password" to password)
            val url="http://47.94.139.212:8080/user/register"
            simplePostUseFrom(url,map)
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
    }
}