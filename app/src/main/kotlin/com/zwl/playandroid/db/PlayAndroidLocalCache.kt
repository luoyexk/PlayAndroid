package com.zwl.playandroid.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.zwl.playandroid.db.dao.ArticleDao
import com.zwl.playandroid.db.entity.article.Article
import java.util.concurrent.Executor

/**
 * Create: 2019-12-31 15:23
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class PlayAndroidLocalCache(private val database: AppDatabase, private val executor: Executor) {

    private var dao: ArticleDao = database.articleDao()

    fun searchArticles(): LiveData<MutableList<Article>> {
        return dao.searchHomeArticle()
    }

    fun destroy() {
        executor.execute { database.close() }
    }

    fun insert(articles: List<Article>, onInserted: () -> Unit) {
        executor.execute {
            dao.insertArticle(articles.toMutableList())
            Log.e("zzzz", "PlayAndroidLocalCache - insert -> size:${articles.size}")
            onInserted()
        }
    }
}