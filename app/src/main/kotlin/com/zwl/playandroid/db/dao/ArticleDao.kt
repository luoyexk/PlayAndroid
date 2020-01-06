package com.zwl.playandroid.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zwl.playandroid.db.entity.article.Article

/**
 * Create: 2019-12-31 18:30
 * version:
 * desc:
 *
 * @author Zouweilin
 */
@Dao
interface ArticleDao {

    /**
     * 插入文章集合,必须在
     * suspend申明为挂起函数
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(list: MutableList<Article>)

    @Query("SELECT * FROM articles ORDER BY publishTime DESC")
    fun searchHomeArticle(): LiveData<MutableList<Article>>
}