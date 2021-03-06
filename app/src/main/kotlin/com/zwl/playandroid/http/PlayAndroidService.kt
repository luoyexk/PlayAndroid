package com.zwl.playandroid.http

import com.zwl.playandroid.BuildConfig
import com.zwl.playandroid.db.entity.article.ArticleData
import com.zwl.playandroid.db.entity.article.ResponseArticleList
import com.zwl.playandroid.http.rx.RxUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


/**
 * Create: 2019-12-28 17:39
 * version:
 * desc:
 *
 * @author Zouweilin
 */
interface PlayAndroidService {

    companion object {

        private fun createOkHttpClient(): OkHttpClient {
            val timeOut = 10L
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            return OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()
        }

        fun create(): PlayAndroidService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PlayAndroidService::class.java)
        }

    }

    // ============================== 1.首页相关 ==============================
    /**
     *  1.1 首页文章列表
    https://www.wanandroid.com/article/list/0/json

    方法：GET
    参数：页码，拼接在连接中，从0开始。

    很多 H5 页面会恶意跳转淘宝等，可以在 webview 的 shouldOverrideUrlLoading 中做一下拦截，非常影响用户体验。

    可直接点击查看示例：https://www.wanandroid.com/article/list/1/json。

    注意：页码从0开始，拼接在链接上。

    其中有两个易混淆的字段:

    "superChapterId": 153,
    "superChapterName": "framework", // 一级分类的名称
    superChapterId其实不是一级分类id，因为要拼接跳转url，内容实际都挂在二级分类下，所以该id实际上是一级分类的第一个子类目的id，拼接后故可正常跳转。

    有两个字段比较容易混淆：

    author 与 shareUser

    网站上的文章可能是某位作者author的，也可能是某位分享人shareUser分享的。

    如果是分享人分享的，author 为 null。

    注意：除了文字标题，链接，其他字段都可能为null，一定要注意布局下发 null 时的显示情况。
     */
    @GET("article/list/{page}/json")
    fun fetchHomeArticleList(@Path("page") page: Int): Call<ResponseArticleList>

    // ============================== 10.广场相关 ==============================

    @GET("user_article/list/{page}/json")
    fun fetchSquareList(@Path("page") page: Int): Call<ResponseArticleList>

}

private fun createError(code: Int, t: Throwable): ResponseError {
    if (t.message == null) {
        return UNKNOWN_ERROR
    }
    return getResponseError(code)
}

fun fetchHomeArticleList(
    service: PlayAndroidService,
    page: Int,
    onSuccess: (data: ArticleData?) -> Unit,
    onError: (error: ResponseError) -> Unit
) {
    service.fetchHomeArticleList(page).enqueue(object : Callback<ResponseArticleList> {
        override fun onFailure(call: Call<ResponseArticleList>, t: Throwable) {
            onError(createError(FETCH_HOME_ARTICLE_LIST_CODE, t))
        }

        override fun onResponse(
            call: Call<ResponseArticleList>,
            response: Response<ResponseArticleList>
        ) {
            if (response.isSuccessful) {
                onSuccess(response.body()?.data)
            } else {
                onError(UNKNOWN_ERROR)
            }
        }
    })
}

fun fetchSquareList(
    service: PlayAndroidService,
    page: Int,
    onSuccess: (data: ArticleData?) -> Unit,
    onError: (error: ResponseError) -> Unit
) {
    service.fetchSquareList(page).enqueue(object : Callback<ResponseArticleList> {
        override fun onFailure(call: Call<ResponseArticleList>, t: Throwable) {
            onError(createError(FETCH_SQUARE_ARTICLE_LIST_CODE, t))
        }

        override fun onResponse(
            call: Call<ResponseArticleList>,
            response: Response<ResponseArticleList>
        ) {
            if (response.isSuccessful) {
                onSuccess(response.body()?.data)
            } else {
                onError(UNKNOWN_ERROR)
            }
        }
    })
}