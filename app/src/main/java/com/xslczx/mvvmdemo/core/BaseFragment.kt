package com.xslczx.mvvmdemo.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(getLayoutResId(), container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    initView()
    super.onViewCreated(view, savedInstanceState)
    initData()
  }

  abstract fun getLayoutResId(): Int

  abstract fun initView()

  abstract fun initData()
}