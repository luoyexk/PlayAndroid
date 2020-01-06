package com.zwl.playandroid.http

/**
 * Create: 2019-12-31 18:11
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class ResponseError(val code: Int, val message: String)

fun getResponseError(code: Int): ResponseError {
    return when (code) {
        FETCH_HOME_ARTICLE_LIST_CODE -> FETCH_HOME_ARTICLE_LIST_ERROR
        else -> UNKNOWN_ERROR
    }
}

val FETCH_HOME_ARTICLE_LIST_CODE = 110
val UNKNOWN_ERROR = ResponseError(-1, "unknown error.")
val FETCH_HOME_ARTICLE_LIST_ERROR = ResponseError(FETCH_HOME_ARTICLE_LIST_CODE, "首页文章列表获取失败")
