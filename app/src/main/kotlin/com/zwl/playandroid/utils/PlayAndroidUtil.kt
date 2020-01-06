package com.zwl.playandroid.utils

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