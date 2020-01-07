package com.zwl.playandroid.adapters

import android.provider.SyncStateContract
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.internal.ViewUtils
import com.zwl.playandroid.R
import com.zwl.playandroid.utils.dpToPx

/**
 * Create: 2020-01-02 11:03
 * version:
 * desc:
 *
 * @author Zouweilin
 */
/*
@BindingAdapter("setOnNavigationItemSelectedListener")
fun setOnNavigationItemSelectedListener(
    view: BottomNavigationView,
    listener: BottomNavigationView.OnNavigationItemReselectedListener
) {
    view.setOnNavigationItemReselectedListener(listener)
}*/

@BindingAdapter("srcAvatar")
fun setAvatar(view: ImageView, link: String?) {
    link ?: return
    val roundedCorners = RoundedCorners(view.resources.getDimension(R.dimen.dp_90).toInt())
    Glide.with(view).load(link)
        .placeholder(R.drawable.ic_person)
        .transform(roundedCorners).into(view)
}

@BindingAdapter("renderHtml")
fun setHtmlText(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    } else {
        view.text = ""
    }
}
