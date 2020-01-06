package com.zwl.playandroid.data

import androidx.lifecycle.LiveData
import com.zwl.playandroid.db.PlayAndroidLocalCache
import com.zwl.playandroid.db.entity.article.Article
import com.zwl.playandroid.db.entity.article.ArticleData
import com.zwl.playandroid.http.PlayAndroidService
import com.zwl.playandroid.http.fetchHomeArticleList

/**
 * Create: 2019-12-31 15:22
 * version:
 * desc:
 *
 * @author Zouweilin
 */
const val PER_PAGE_SIZE = 20
class PlayAndroidRepository(
    private val service: PlayAndroidService,
    private val cache: PlayAndroidLocalCache
) {

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 0

    private var isRequestInProgress = false
    fun requestMore() {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) {
            return
        }
        isRequestInProgress = true
        fetchHomeArticleList(service, lastRequestedPage, { data: ArticleData? ->
            if (data == null) {
                isRequestInProgress = false
                return@fetchHomeArticleList
            }
            cache.insert(data.datas){
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, { error ->
            isRequestInProgress = false

        })
    }

    fun destroy() {
        cache.destroy()
    }

    fun requestPage(): LiveData<MutableList<Article>> {
        return cache.searchArticles()
    }

}