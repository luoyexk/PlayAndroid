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
        FETCH_SQUARE_ARTICLE_LIST_CODE -> FETCH_SQUARE_ARTICLE_LIST_ERROR
        LOGIN_CODE -> LOGIN_ERROR
        FAVOURITE_CODE -> FAVOURITE_ARTICLE_ERROR
        else -> UNKNOWN_ERROR
    }
}

val FETCH_HOME_ARTICLE_LIST_CODE = 11
val LOGIN_CODE = 51
val FAVOURITE_CODE = 61
val FETCH_SQUARE_ARTICLE_LIST_CODE = 101

val UNKNOWN_ERROR = ResponseError(-1, "unknown error.")
val FETCH_HOME_ARTICLE_LIST_ERROR = ResponseError(FETCH_HOME_ARTICLE_LIST_CODE, "首页文章获取失败")
val LOGIN_ERROR = ResponseError(FETCH_HOME_ARTICLE_LIST_CODE, "首页文章获取失败")
val FAVOURITE_ARTICLE_ERROR = ResponseError(FAVOURITE_CODE, "收藏文章获取失败")
val FETCH_SQUARE_ARTICLE_LIST_ERROR = ResponseError(FETCH_SQUARE_ARTICLE_LIST_CODE, "广场文章获取失败")
