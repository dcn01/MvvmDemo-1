package com.xslczx.mvvmdemo.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(getLayoutResId())
    initData(savedInstanceState)
  }

  abstract fun getLayoutResId(): Int
  abstract fun initData(savedInstanceState: Bundle?)
}