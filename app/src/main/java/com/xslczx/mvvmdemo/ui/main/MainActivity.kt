package com.xslczx.mvvmdemo.ui.main

import android.util.Log
import com.xslczx.mvvmdemo.R
import com.xslczx.mvvmdemo.core.BaseActivity
import kotlinx.android.synthetic.main.title_layout.mToolbar

class MainActivity : BaseActivity() {

  override fun getLayoutResId(): Int {
    return R.layout.activity_main
  }

  override fun initView() {
    mToolbar.title = "竖屏壁纸"
  }

  override fun initData() {
    supportFragmentManager.beginTransaction()
        .add(R.id.container, MainFragment())
        .runOnCommit { Log.v("logger", "runOnCommit") }
        .commitAllowingStateLoss()
  }
}
