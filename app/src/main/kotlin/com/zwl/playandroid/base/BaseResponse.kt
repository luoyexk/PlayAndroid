package com.zwl.playandroid.base

/**
 * Create: 2019-12-28 17:18
 * version:
 * desc:
 *
 * @author Zouweilin
 */
open class BaseResponse<T>(
    val data: T? = null,
    val errorCode: Int = -1,
    val errorMsg: String = ""
)