package com.xslczx.mvvmdemo.ext

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xslczx.mvvmdemo.model.adapter.RecyclerItemClickListener
import com.xslczx.mvvmdemo.model.adapter.RecyclerViewItemClickListener

fun RecyclerView.addOnItemClick(listener: RecyclerViewItemClickListener) {
  this.addOnChildAttachStateChangeListener(RecyclerItemClickListener(this, listener, null))
}

/**
 * 防止快速点击
 */
inline fun View.singleClick(
  delayMillis: Long = 400,
  crossinline onClick: (View) -> Unit
) {
  this.setOnClickListener {
    this.isClickable = false
    onClick(this)
    this.postDelayed({
      this.isClickable = true
    }, delayMillis)
  }
}

fun View.show() {
  visibility = View.VISIBLE
}

fun View.hide() {
  visibility = View.GONE
}

fun View.showOrHide(show: Boolean) = if (show) show() else hide()
