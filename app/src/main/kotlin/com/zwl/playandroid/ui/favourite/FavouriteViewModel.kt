package com.zwl.playandroid.ui.favourite

import com.zwl.playandroid.base.BaseViewModel
import com.zwl.playandroid.data.PlayAndroidRepository

/**
 * Create: 2020-01-07 14:33
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class FavouriteViewModel(val repository: PlayAndroidRepository) :BaseViewModel() {
    override fun destroy() {

    }

    fun requestFavourite() {
        repository.requestFavourite()
    }
}