package com.dish.app.uikit.extensions

import android.content.res.Resources

val Int.pxToDp: Int
    get() {
        return (this / Resources.getSystem().displayMetrics.density).toInt()
    }

val Int.dpToPx: Int
    get() {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }