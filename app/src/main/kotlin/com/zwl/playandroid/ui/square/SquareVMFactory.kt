package com.zwl.playandroid.ui.square

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * Create: 2020-01-03 15:18
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class SquareVMFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SquareViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SquareViewModel() as T
        }
        throw IllegalArgumentException("SquareVMFactory - Unknown ViewModel class")

    }
}