package com.xslczx.mvvmdemo.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import com.xslczx.mvvmdemo.R
import com.xslczx.mvvmdemo.core.BaseActivity
import com.xslczx.mvvmdemo.ext.popBackStackImmediate
import kotlinx.android.synthetic.main.title_layout.mToolbar

class MainActivity : BaseActivity() {

  override fun getLayoutResId(): Int {
    return R.layout.activity_main
  }

  override fun initData(savedInstanceState: Bundle?) {
    mToolbar.title = "竖屏壁纸"
    val mf =
      if (savedInstanceState == null) MainFragment()
      else supportFragmentManager.findFragmentByTag(
          MainFragment::class.java.simpleName
      ) ?: MainFragment()

    supportFragmentManager.beginTransaction()
        .add(R.id.container, mf)
        .runOnCommit { Log.v("logger", "runOnCommit") }
        .setMaxLifecycle(mf, Lifecycle.State.RESUMED)
        .commitAllowingStateLoss()
  }

  /**
   * 返回时优先移除栈顶fragment，无fragment时响应super
   */
  override fun onBackPressed() {
    if (!popBackStackImmediate()) {
      super.onBackPressed()
    }
  }
}
