package com.zwl.playandroid.ui.square

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.zwl.playandroid.annotation.REFRESH
import com.zwl.playandroid.annotation.REQUEST_MORE
import com.zwl.playandroid.base.BaseViewModel
import com.zwl.playandroid.data.PlayAndroidRepository
import com.zwl.playandroid.db.entity.article.Article
import com.zwl.playandroid.ui.PlayAndroidViewModel
import com.zwl.playandroid.ui.VISIBLE_THRESHOLD

/**
 * Create: 2020-01-03 15:17
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class SquareViewModel(val repository: PlayAndroidRepository) : BaseViewModel() {

    private val _source = mutableListOf<Article>()
    val articles = MutableLiveData<MutableList<Article>>()

    init {
        articles.value = mutableListOf()
    }

    fun refresh() {
        startRequest()
        _source.clear()
        articles.postValue(_source)
        repository.requestSquareArticle(REFRESH, {
            it?.datas?.toMutableList()?.also { list ->
                _source.addAll(list)
                articles.postValue(_source)
            }
            requestIdle()
        }, {
            requestIdle()
        })
    }

    fun requestMore() {
        startRequest()
        repository.requestSquareArticle(REQUEST_MORE, {
            it?.datas?.toMutableList()?.also { list ->
                _source.addAll(list)
                articles.postValue(_source)
            }
            requestIdle()
        }, {
            requestIdle()
        })
    }

    override fun destroy() {


    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItem: Int, totalItemCount: Int) {
        if (isRequestStateIdle() && lastVisibleItem + VISIBLE_THRESHOLD > totalItemCount) {
            requestMore()
        }
    }

}