package com.zwl.playandroid.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.Dimension

/**
 * Create: 2019-12-31 15:32
 * version:
 * desc:
 *
 * @author Zouweilin
 */
fun checkNotEmpty(string: String) {
    if (string.isEmpty()) {
        throw IllegalArgumentException("The parameter must be not empty.")
    }
}

fun dpToPx(context: Context, @Dimension(unit = Dimension.DP) dp: Int): Float {
    val r = context.resources
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
}