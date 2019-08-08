package com.xslczx.mvvmdemo.model.bean

import com.xslczx.mvvmdemo.model.base.BaseBean

data class WpBean(
  val preview: String,
  val thumb: String,
  val img: String,
  val id: String,
  val desc: String
) : BaseBean(id)