package com.zwl.playandroid.db

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences

/**
 * Create: 2019-12-31 15:41
 * version:
 * desc:
 *
 * @author Zouweilin
 */
object UserMappingDatabase {

    private const val KEY_FILE_NAME = "UserMapping"
    private const val KEY_CURRENT_USER = "curUserName"
    private const val KEY_COOKIE = "cookie"
    private lateinit var sharedPreferences: EncryptedSharedPreferences
    fun setUp(context: Context) {
        val masterKeyAlias = "alias_play_android"
        sharedPreferences = EncryptedSharedPreferences
            .create(
                KEY_FILE_NAME,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            ) as EncryptedSharedPreferences
    }

    fun saveCurrentUserName(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_CURRENT_USER, user.userName)
        editor.apply()
    }

    fun getCurrentUserName(): String? {
        return sharedPreferences.getString(KEY_CURRENT_USER, null)
    }

    fun saveUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.apply {
            val userKey = createTokenKey(user.userName)
            val userToken = createToken(user)
            putString(userKey, userToken)
        }
        editor.apply()
    }

    fun getUser(): User? {
        val currentUserName = getCurrentUserName() ?: return null
        val userKey = createTokenKey(currentUserName)
        val token = sharedPreferences.getString(userKey, null) ?: return null
        return convertToken(token)
    }

    /**
     * 保存当前登陆用户的cookie
     */
    fun saveCookie(cookie: String) {
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(KEY_COOKIE, cookie)
        }.apply()
    }

    fun getCookie(): String? {
        return sharedPreferences.getString(KEY_COOKIE, null)
    }

    fun removeCookie() {
        sharedPreferences.edit().remove(KEY_COOKIE).apply()
    }

}