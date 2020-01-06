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
}