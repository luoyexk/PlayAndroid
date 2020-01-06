package com.zwl.playandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Create: 2020-01-02 14:33
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class SimpleDataViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseNameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DatabaseNameViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}