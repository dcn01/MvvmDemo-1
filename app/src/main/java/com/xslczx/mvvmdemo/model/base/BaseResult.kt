package com.xslczx.mvvmdemo.model.base

data class BaseResult<out T>(val code: Int, val msg: String, val res: T)