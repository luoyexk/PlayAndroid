package com.zwl.playandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Create: 2020-01-02 14:28
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class DatabaseNameViewModel : ViewModel() {

    var dbName = MutableLiveData<String>()
        private set

    fun postDbName(dbName: String) {
        this.dbName.value = dbName
    }
}