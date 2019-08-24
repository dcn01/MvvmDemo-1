package com.xslczx.mvvmdemo.core

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel(), LifecycleObserver {

  val mException: MutableLiveData<Throwable> = MutableLiveData()

  private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch { block() }
  }

  suspend fun <T> launchIO(block: suspend CoroutineScope.() -> T) {
    withContext(Dispatchers.IO) {
      block
    }
  }

  fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {
    launchOnUI {
      tryCatch(tryBlock, {}, {}, true)
    }
  }

  fun launchOnUITryCatch(
    tryBlock: suspend CoroutineScope.() -> Unit,
    catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
    finallyBlock: suspend CoroutineScope.() -> Unit,
    handleCancellationExceptionManually: Boolean
  ) {
    launchOnUI {
      tryCatch(tryBlock, catchBlock, finallyBlock, handleCancellationExceptionManually)
    }
  }

  fun launchOnUITryCatch(
    tryBlock: suspend CoroutineScope.() -> Unit,
    handleCancellationExceptionManually: Boolean = false
  ) {
    launchOnUI {
      tryCatch(tryBlock, {}, {}, handleCancellationExceptionManually)
    }
  }

  private suspend fun tryCatch(
    tryBlock: suspend CoroutineScope.() -> Unit,
    catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
    finallyBlock: suspend CoroutineScope.() -> Unit,
    handleCancellationExceptionManually: Boolean = false
  ) {
    coroutineScope {
      try {
        tryBlock()
      } catch (e: Throwable) {
        if (e !is CancellationException || handleCancellationExceptionManually) {
          mException.value = e
          catchBlock(e)
        } else {
          throw e
        }
      } finally {
        finallyBlock()
      }
    }
  }
}
