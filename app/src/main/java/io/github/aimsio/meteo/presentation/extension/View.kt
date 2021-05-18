package me.meikiem.pixabay.presentation.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false) =
    LayoutInflater.from(context).inflate(layout, this, attachToRoot)

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.showSnackbar(
    msgId: Int,
    length: Int,
    actionMessageId: Int,
    action: (View) -> Unit
) {
    showSnackbar(context.getString(msgId), length, context.getString(actionMessageId), action)
}

fun View.showSnackbar(
    msg: String,
    length: Int,
    actionMessageId: Int,
    action: (View) -> Unit
) {
    showSnackbar(msg, length, context.getString(actionMessageId), action)
}

fun View.showSnackbar(
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(this, msg, length)
    snackbar.setActionTextColor(Color.parseColor("#FFBB86FC"))
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(this)
        }.show()
    }
}

internal fun ImageView.load(
    url: String,
    @DrawableRes placeholderResourceId: Int
) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholderResourceId)
        .into(this)
}

internal fun ImageView.load(
    url: String,
    transformation: Transformation<Bitmap>,
    @DrawableRes placeholderResourceId: Int
) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholderResourceId)
        .apply(RequestOptions().transform(transformation))
        .into(this)
}

internal fun ImageView.load(
    @DrawableRes imageResource: Int

) {
    Glide.with(context)
        .load(imageResource)
        .into(this)
}

fun View.hideKeyboard() {
    val inputMethodService = context.getSystemService(Context.INPUT_METHOD_SERVICE)
    (inputMethodService as? InputMethodManager)?.hideSoftInputFromWindow(
        windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun SwipeRefreshLayout.setSwipeRefreshColors(colorResIds: IntArray) {
    setColorSchemeColors(*colorResIds)
}
