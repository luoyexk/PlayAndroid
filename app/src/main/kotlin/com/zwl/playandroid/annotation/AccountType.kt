package com.zwl.playandroid.annotation

import androidx.annotation.IntDef
import com.zwl.playandroid.annotation.AccountType.Companion.FORGOT_TYPE
import com.zwl.playandroid.annotation.AccountType.Companion.LOGIN_TYPE
import com.zwl.playandroid.annotation.AccountType.Companion.REGISTER_TYPE

/**
 * Create: 2020-01-07 09:13
 * version:
 * desc: 用户账号功能定义
 *
 * @author Zouweilin
// */
@IntDef(LOGIN_TYPE, REGISTER_TYPE, FORGOT_TYPE)
@Retention(AnnotationRetention.SOURCE)
annotation class AccountType {
    companion object {
        const val REGISTER_TYPE = 1
        const val LOGIN_TYPE = 10
        const val FORGOT_TYPE = 20
    }
}

///**
// * 登陆
// */
//const val LOGIN_TYPE = 10
///**
// * 注册
// */
//const val REGISTER_TYPE = 20
///**
// * 忘记密码,准备重置
// */
//const val FORGOT_TYPE = 30