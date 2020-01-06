package com.zwl.playandroid.ui.browser

import androidx.lifecycle.MutableLiveData
import com.zwl.playandroid.base.BaseViewModel
import com.zwl.playandroid.data.PlayAndroidRepository
import com.zwl.playandroid.db.entity.article.Article

/**
 * Create: 2020-01-06 10:50
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class BrowserViewModel(val repository: PlayAndroidRepository) : BaseViewModel() {

    val article = MutableLiveData<Article>()
    // todo 持久化浏览记录,好为后续未浏览做准备
    val browserRecord = MutableLiveData<MutableMap<String, Pair<Int, Int>>>()
    private var currentBrowserLink: String? = null

    override fun destroy() {

    }

    fun saveBrowser(scrollX: Int, scrollY: Int) {
        var map = browserRecord.value
        if (map == null) {
            map = mutableMapOf()
            browserRecord.value = map
        }
        article.value?.title?.also {
            map[it] = scrollX to scrollY
        }
    }

    fun updateArticle(article: Article) {
        this.article.value = article
        currentBrowserLink = article.link
    }
}