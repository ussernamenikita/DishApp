package com.dish.app.uikit.skeleton

import android.animation.ValueAnimator
import android.graphics.drawable.Animatable


open class SkeletonAnimation : Animatable {

    companion object {
        private const val ANIMATE_ALPHA_FROM = 1.0f
        private const val ANIMATE_ALPHA_TO = 0.5f
        private const val ANIMATION_DURATION_MS = 800L
    }

    private var animator: ValueAnimator? = null

    var callback: ((animatedValue: Float) -> Unit)? = null

    val animatedValue: Float
        get() {
            return (animator?.animatedValue as Float?) ?: 0f
        }

    override fun start() {
        if (isRunning) return
        animator?.cancel()
        animator = createAndStart()
    }

    override fun stop() {
        if (!isRunning) return
        animator?.cancel()
        animator = null
    }

    override fun isRunning(): Boolean {
        return animator?.isRunning == true
    }

    private fun createAndStart(): ValueAnimator? {
        // 0  .. (fade in) .. ANIMATION_DURATION_MS .. (fade out) .. ANIMATION_DURATION_MS * 2
        val startPlayTime = System.currentTimeMillis() % (ANIMATION_DURATION_MS * 2)
        return ValueAnimator
            .ofFloat(ANIMATE_ALPHA_FROM, ANIMATE_ALPHA_TO)
            .apply {
                currentPlayTime = startPlayTime
                repeatMode = ValueAnimator.REVERSE
                repeatCount = ValueAnimator.INFINITE
                addUpdateListener { callback?.invoke(it.animatedValue as Float) }
                duration = ANIMATION_DURATION_MS
                start()
            }
    }
}