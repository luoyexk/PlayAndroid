package com.zwl.playandroid.http.rx

import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create: 2019-12-31 17:57
 * version:
 * desc:
 *
 * @author Zouweilin
 */
object RxUtil {

    /**
     * RxJava Android一般耗时任务线程切换
     */
    @JvmStatic
    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    @JvmStatic
    fun <T> applyIOSchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
        }
    }

    @JvmStatic
    fun <T> singleApplySchedulers(): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    @JvmStatic
    fun <T> maybeSchedulers(): MaybeTransformer<T, T> {
        return MaybeTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}