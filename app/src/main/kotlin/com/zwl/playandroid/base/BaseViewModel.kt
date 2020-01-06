package com.zwl.playandroid.base

import androidx.lifecycle.ViewModel

/**
 * Create: 2020-01-02 15:07
 * version:
 * desc:
 *
 * @author Zouweilin
 */
abstract class BaseViewModel : ViewModel() {

    abstract fun destroy()

    enum class RequestState {
        OnRequest,
        IDLE,
    }

    private var requestState = RequestState.IDLE
    protected fun startRequest() {
        requestState = RequestState.OnRequest
    }

    protected fun requestIdle() {
        requestState = RequestState.IDLE
    }

    protected fun isRequestStateIdle() = requestState == RequestState.IDLE
}