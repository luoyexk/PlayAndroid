package com.zwl.playandroid

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.zwl.playandroid.data.PlayAndroidRepository
import com.zwl.playandroid.db.AppDatabase
import com.zwl.playandroid.db.PlayAndroidLocalCache
import com.zwl.playandroid.http.PlayAndroidService
import com.zwl.playandroid.ui.PlayAndroidViewModelFactory
import com.zwl.playandroid.ui.SimpleDataViewModelFactory
import com.zwl.playandroid.ui.login.AccountViewModel
import com.zwl.playandroid.ui.login.AccountViewModelFactory
import com.zwl.playandroid.ui.square.SquareVMFactory
import com.zwl.playandroid.ui.square.SquareViewModel
import java.util.concurrent.Executors

/**
 * Create: 2020-01-02 11:57
 * version:
 * desc:
 *
 * @author Zouweilin
 */
object Injections {

    fun providerAccountViewModelFactory(): ViewModelProvider.Factory {
        return AccountViewModelFactory()
    }

    fun providerAppViewModelFactory(
        context: Context,
        dbName: String
    ): ViewModelProvider.Factory {
        return PlayAndroidViewModelFactory(providerRepository(context, dbName))
    }

    /**
     * 创建数据仓库
     */
    private fun providerRepository(context: Context, dbName: String): PlayAndroidRepository {
        return PlayAndroidRepository(PlayAndroidService.create(), providerCache(context, dbName))
    }

    /**
     * 创建本地数据库
     */
    private fun providerCache(context: Context, dbName: String): PlayAndroidLocalCache {
        val database = AppDatabase.getInstance(context, dbName)
        return PlayAndroidLocalCache(database, Executors.newSingleThreadExecutor())
    }

    fun providerSquareViewModelFactory(dbName: String): ViewModelProvider.Factory {
        return SquareVMFactory()
    }


}