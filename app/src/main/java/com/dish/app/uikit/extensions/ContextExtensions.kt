package com.dish.app.uikit.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.dish.app.uikit.skeleton.SkeletonDrawable


@ColorInt
fun Context.getColorByAttr(@AttrRes colorAttribute: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(colorAttribute, typedValue, true)
    return typedValue.data
}

fun Context.getLoadingPlaceholder(): Drawable {
    return SkeletonDrawable(this)
}