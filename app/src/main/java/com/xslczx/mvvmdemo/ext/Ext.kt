package com.xslczx.mvvmdemo.ext

import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.SimpleItemAnimator
import com.xslczx.mvvmdemo.model.base.BaseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

suspend fun executeResponse(
  response: BaseResult<Any>,
  successBlock: suspend CoroutineScope.() -> Unit,
  errorBlock: suspend CoroutineScope.() -> Unit
) {
  coroutineScope {
    if (response.code == -1) errorBlock()
    else successBlock()
  }
}

fun ItemAnimator.closeAnim() {
  this.apply {
    addDuration = 0
    changeDuration = 0
    moveDuration = 0
    removeDuration = 0
    (this as SimpleItemAnimator).supportsChangeAnimations = false
  }
}