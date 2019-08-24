package com.xslczx.mvvmdemo.model.base

open class BaseRepository {

  suspend fun <T : Any> apiCall(call: suspend () -> BaseResult<T>): BaseResult<T> {
    return call.invoke()
  }
}