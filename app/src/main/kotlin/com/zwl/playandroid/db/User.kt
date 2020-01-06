package com.zwl.playandroid.db

import androidx.room.Entity
import com.google.gson.Gson
import org.json.JSONObject
import java.io.Serializable
import java.util.*

/**
 * Create: 2019-12-31 15:41
 * version:
 * desc:
 *
 * @author Zouweilin
 */
data class User(val userName: String, val password: String) : Serializable {
    val dbName = createDbName(userName)
    val tokenCreateTime = createTokenTime()

    private fun createTokenTime() = System.currentTimeMillis()
}

fun createTokenKey(userName: String): String = userName + "_token"
fun createToken(user: User): String {
    return Gson().toJson(user)
}

fun convertToken(string: String): User {
    return Gson().fromJson(string, User::class.java)
}

fun createDbName(userName: String): String {
    // todo 加密成不含特殊字符的名称
    return userName
}