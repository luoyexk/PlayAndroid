package com.zwl.playandroid.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Create: 2020-01-03 11:22
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class AccountViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AccountViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class -> AccountViewModelFactory")
    }
}