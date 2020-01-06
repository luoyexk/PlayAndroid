package com.zwl.playandroid.base

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

/**
 * Create: 2020-01-02 14:48
 * version:
 * desc:
 *
 * @author Zouweilin
 */
abstract class BaseFragment : Fragment() {

    open val _tag = this::class.java.simpleName

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("zzzz", "BaseFragment - onSaveInstanceState -> name:$_tag")
    }
}