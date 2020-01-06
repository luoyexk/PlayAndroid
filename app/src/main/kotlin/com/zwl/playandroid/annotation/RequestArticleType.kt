package com.zwl.playandroid.annotation

import android.view.ViewDebug
import androidx.annotation.IntDef

/**
 * Create: 2020-01-06 14:56
 * version:
 * desc:
 *
 * @author Zouweilin
 */
@IntDef(REFRESH, REQUEST_MORE)
@Retention(AnnotationRetention.SOURCE)
annotation class RequestArticleType {}

const val REFRESH = 0
const val REQUEST_MORE = 1