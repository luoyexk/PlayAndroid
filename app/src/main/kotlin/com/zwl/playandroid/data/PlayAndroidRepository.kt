package com.zwl.playandroid.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.zwl.playandroid.annotation.REQUEST_MORE
import com.zwl.playandroid.annotation.RequestArticleType
import com.zwl.playandroid.db.CookieHelper
import com.zwl.playandroid.db.PlayAndroidLocalCache
import com.zwl.playandroid.db.User
import com.zwl.playandroid.db.UserMappingDatabase
import com.zwl.playandroid.db.entity.account.Login
import com.zwl.playandroid.db.entity.article.Article
import com.zwl.playandroid.db.entity.article.ArticleData
import com.zwl.playandroid.http.*

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
            cache.insert(data.datas) {
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

    private var lastRequestedPageOfSquare = 0
    private var isRequestSquare = false

    fun requestSquareArticle(
        @RequestArticleType type: Int,
        onSuccess: (data: ArticleData?) -> Unit,
        onError: (error: ResponseError) -> Unit
    ) {
        if (isRequestSquare) {
            return
        }
        isRequestSquare = true
        val page = if (type == REQUEST_MORE) lastRequestedPageOfSquare else 0
        fetchSquareList(service, page, { data: ArticleData? ->
            if (type == REQUEST_MORE) {
                lastRequestedPageOfSquare++
            }
            isRequestSquare = false
            onSuccess(data)
        }, { error ->
            isRequestSquare = false
            onError(error)
        })
    }

    fun requestLogin(user: User, onSuccess: () -> Unit, onError: (error: ResponseError) -> Unit) {
        login(service, user, { data: Login?, cookie: String? ->
            if (cookie != null) {
                CookieHelper.saveCookie(cookie)
                onSuccess()
            }
        }, { error ->
            CookieHelper.removeCookie()
        })
    }

    fun requestFavourite() {
        fetchFavourite(service, 0, {
            Log.e("zzzz","PlayAndroidRepository - requestFavourite -> 收藏:$it" )
        }, {

        })
    }

}