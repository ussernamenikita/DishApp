package com.dish.app.uikit.skeleton

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import com.dish.app.R
import com.dish.app.uikit.extensions.dpToPx
import com.dish.app.uikit.extensions.getColorByAttr

class SkeletonDrawable(
    context: Context,
    private val animator: SkeletonAnimation = SkeletonAnimation(),
) : Drawable(), Animatable by animator {

    companion object {
        private val ROUND_RADIUS_PX = 12.dpToPx.toFloat()
        private const val BACKGROUND_COLOR_ATTR = R.attr.dishAppSkeletonColor
        private const val ALPHA_MAX = 255
    }

    private var currentBounds: RectF = RectF()

    private val paint: Paint = Paint().apply {
        isAntiAlias = true
        color = context.getColorByAttr(BACKGROUND_COLOR_ATTR)
    }

    init {
        animator.callback = { invalidateSelf() }
    }

    override fun setAlpha(alpha: Int) = Unit

    override fun setColorFilter(colorFilter: ColorFilter?) = Unit

    override fun setVisible(visible: Boolean, restart: Boolean): Boolean {
        if (visible) {
            start()
        } else {
            stop()
        }
        return super.setVisible(visible, restart)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun getOpacity(): Int = PixelFormat.OPAQUE

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        currentBounds = RectF(bounds)
    }

    override fun draw(canvas: Canvas) {
        paint.alpha = (ALPHA_MAX * animator.animatedValue).toInt()
        canvas.drawRoundRect(currentBounds, ROUND_RADIUS_PX, ROUND_RADIUS_PX, paint)
    }
}
