package com.xslczx.mvvmdemo.model.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

suspend fun executeResponse(
    response: BaseResult<Any>, successBlock: suspend CoroutineScope.() -> Unit,
    errorBlock: suspend CoroutineScope.() -> Unit
) {
    coroutineScope {
        if (response.code == -1) errorBlock()
        else successBlock()
    }
}