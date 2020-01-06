package com.zwl.playandroid.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.savedstate.SavedStateRegistry
import com.zwl.playandroid.base.BaseViewModel
import com.zwl.playandroid.data.PlayAndroidRepository
import com.zwl.playandroid.db.entity.article.Article

class PlayAndroidViewModel(val repository: PlayAndroidRepository) : BaseViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val request: MutableLiveData<Int> = MutableLiveData(0)
    val articleList: LiveData<MutableList<Article>> = repository.requestPage()

    fun requestMore() {
        repository.requestMore()
    }

    override fun destroy() {
        repository.destroy()
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItem: Int, totalItemCount: Int) {
        val i = lastVisibleItem + VISIBLE_THRESHOLD
//        if (i > totalItemCount) {
//            requestMore()
//        }
    }

}
