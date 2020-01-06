package com.zwl.playandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zwl.playandroid.db.dao.ArticleDao
import com.zwl.playandroid.db.entity.article.Article
import com.zwl.playandroid.utils.checkNotEmpty

/**
 * Create: 2019-12-31 15:25
 * version:
 * desc:
 *
 * @author Zouweilin
 */
@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        /**
         * 生成用户个人数据库
         * 只有登录,获取到用户账号后,才能创建数据库
         */
        fun getInstance(context: Context, databaseName: String): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, databaseName).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, databaseName: String): AppDatabase {
            checkNotEmpty(databaseName)
            return Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
        }
    }
}