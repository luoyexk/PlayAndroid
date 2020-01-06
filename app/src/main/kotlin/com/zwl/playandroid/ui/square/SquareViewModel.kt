package com.zwl.playandroid.ui.square

import androidx.lifecycle.MutableLiveData
import com.zwl.playandroid.base.BaseViewModel

/**
 * Create: 2020-01-03 15:17
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class SquareViewModel : BaseViewModel() {

    private var a = 1

    val title = MutableLiveData<String>()

    fun add() {
        title.value = a++.toString()
    }

    override fun destroy() {


    }
}