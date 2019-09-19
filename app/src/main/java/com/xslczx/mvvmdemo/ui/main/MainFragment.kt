package com.xslczx.mvvmdemo.ui.main

import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.xslczx.mvvmdemo.R
import com.xslczx.mvvmdemo.core.BaseVMFragment
import com.xslczx.mvvmdemo.model.adapter.AverageGridDecoration
import com.xslczx.mvvmdemo.model.adapter.WpAdapter
import com.xslczx.mvvmdemo.model.base.closeAnim
import kotlinx.android.synthetic.main.fragment_main.recycleView
import kotlinx.android.synthetic.main.fragment_main.refreshLayout
import kotlin.LazyThreadSafetyMode.NONE

class MainFragment : BaseVMFragment<MainViewModel>() {

  private val mAdapter: WpAdapter by lazy(NONE) {
    WpAdapter()
  }

  override fun providerVMClass(): Class<MainViewModel> = MainViewModel::class.java

  override fun getLayoutResId(): Int {
    return R.layout.fragment_main
  }

  override fun initView() {
    recycleView.run {
      layoutManager = GridLayoutManager(requireContext(), 3)
      adapter = mAdapter
      addItemDecoration(AverageGridDecoration(5, 5))
      itemAnimator?.closeAnim()
    }
    refreshLayout.apply {
      setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
      setOnRefreshListener {
        loadPagedList(true)
      }
    }
  }

  override fun initData() {
    loadPagedList(false)
  }

  private fun loadPagedList(clear: Boolean) {
    takeIf { clear }.let {
      mAdapter.submitList(null)
    }
    mViewModel.newPagedList()
        .observe(this@MainFragment, Observer {
          mAdapter.submitList(it)
        })
  }

  override fun startObserve() {
    super.startObserve()
    mViewModel.run {
      refresh.observe(this@MainFragment, Observer {
        refreshLayout.isRefreshing = it
      })
      loadMore.observe(this@MainFragment, Observer {
      })
    }
  }
}