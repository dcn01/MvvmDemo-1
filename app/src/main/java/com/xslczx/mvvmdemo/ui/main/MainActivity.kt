package com.xslczx.mvvmdemo.ui.main

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.xslczx.mvvmdemo.R
import com.xslczx.mvvmdemo.core.BaseVMActivity
import com.xslczx.mvvmdemo.model.adapter.AverageGridDecoration
import com.xslczx.mvvmdemo.model.adapter.WpAdapter
import kotlinx.android.synthetic.main.activity_main.recycleView
import kotlinx.android.synthetic.main.title_layout.mToolbar
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : BaseVMActivity<MainViewModel>() {

  private val mAdapter: WpAdapter by lazy(NONE) {
    WpAdapter()
  }

  override fun providerVMClass() = MainViewModel::class.java

  override fun getLayoutResId(): Int {
    return R.layout.activity_main
  }

  override fun initView() {
    mToolbar.title = "竖屏壁纸"
  }

  override fun initData() {
    recycleView.run {
      layoutManager = GridLayoutManager(this@MainActivity, 3)
      adapter = mAdapter
      addItemDecoration(AverageGridDecoration(5, 5))
    }
  }

  override fun startObserve() {
    super.startObserve()
    mViewModel.liveData.observe(this, Observer { mAdapter.submitList(it) })
  }
}
