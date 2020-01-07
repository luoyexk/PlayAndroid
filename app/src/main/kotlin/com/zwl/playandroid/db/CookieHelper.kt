package com.zwl.playandroid.db

import com.zwl.playandroid.BuildConfig
import okhttp3.Cookie
import okhttp3.HttpUrl

/**
 * Create: 2020-01-07 11:57
 * version:
 * desc:
 *
 * @author Zouweilin
 */
object CookieHelper {

    fun saveCookie(cookie: String?) {
        cookie ?: return
        UserMappingDatabase.saveCookie(cookie)
    }

    fun getCookie(): Cookie? {
        val cookie = UserMappingDatabase.getCookie() ?: return null
        val httpUrl = HttpUrl.parse(BuildConfig.BASE_URL) ?: return null
        return Cookie.parse(httpUrl, cookie)
    }

    fun removeCookie() {
        UserMappingDatabase.removeCookie()
    }
}