package com.dish.app.uikit.skeleton

import android.view.View
import androidx.core.view.isVisible


class ViewSkeletonAnimation : SkeletonAnimation(), View.OnAttachStateChangeListener {

    private var attachedToView: View? = null

    init {
        callback = { attachedToView?.alpha = it }
    }

    override fun onViewAttachedToWindow(v: View) = Unit

    override fun onViewDetachedFromWindow(v: View) {
        stop()
    }

    fun showAndStartAnimation(start: Boolean, attachedTo: View) {
        if (start) {
            start()
        } else {
            stop()
        }
        attach(attachedTo)
        attachedTo.isVisible = start
    }

    private fun attach(attachedTo: View) {
        if (this.attachedToView == attachedTo) return
        detach(this.attachedToView)
        attachedTo.addOnAttachStateChangeListener(this)
        attachedToView = attachedTo
    }

    private fun detach(view: View?) {
        view?.removeOnAttachStateChangeListener(this)
        attachedToView = null
    }
}