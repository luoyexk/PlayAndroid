package com.zwl.playandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zwl.playandroid.data.PlayAndroidRepository
import com.zwl.playandroid.ui.browser.BrowserViewModel

/**
 * Create: 2020-01-02 12:08
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class PlayAndroidViewModelFactory(private val repository: PlayAndroidRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayAndroidViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayAndroidViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(BrowserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BrowserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}