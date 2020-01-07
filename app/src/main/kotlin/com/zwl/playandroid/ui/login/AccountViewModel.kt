package com.zwl.playandroid.ui.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.zwl.playandroid.annotation.AccountType
import com.zwl.playandroid.base.BaseViewModel
import com.zwl.playandroid.data.PlayAndroidRepository
import com.zwl.playandroid.db.User
import kotlin.reflect.typeOf

/**
 * Create: 2020-01-03 11:20
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class AccountViewModel(val repository: PlayAndroidRepository) : BaseViewModel() {

    val avatar = MutableLiveData<String>()
    val modeType = MutableLiveData<@com.zwl.playandroid.annotation.AccountType Int>()
    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    init {
        modeType.value = AccountType.LOGIN_TYPE
        modeType.observeForever {
            Log.e("zzzz", "AccountViewModel -  -> it:$it")
        }
    }

    private fun prepareLoginView() {
        modeType.value = AccountType.LOGIN_TYPE
    }

    private fun prepareRegisterView() {
        modeType.value = AccountType.REGISTER_TYPE
    }

    private fun prepareForgotPasswordView() {
        modeType.value = AccountType.FORGOT_TYPE
    }

    override fun destroy() {

    }

    /**
     * 只支持登陆/注册切换,忘记密码不支持
     */
    fun changeType(type: Int) {
        if (type == AccountType.LOGIN_TYPE) {
            prepareRegisterView()
        }
        if (type == AccountType.REGISTER_TYPE) {
            prepareLoginView()
        }
    }

    fun ensure(userName: String?, password: String?, confirmPassword: String?) {
        Log.e("zzzz", "AccountViewModel - ensure -> n:$userName p:$password cp:$confirmPassword")
        userName ?: return
        password ?: return
        if (isLogin()) {
            repository.requestLogin(User(userName, password),{

            },{

            })
        }else if (isRegister()) {

        }
    }

    private fun isLogin(): Boolean {
        return modeType.value == AccountType.LOGIN_TYPE
    }

    private fun isRegister(): Boolean {
        return modeType.value == AccountType.REGISTER_TYPE
    }
}