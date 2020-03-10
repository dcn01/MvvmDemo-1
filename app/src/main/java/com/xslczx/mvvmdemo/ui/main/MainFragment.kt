package com.xslczx.mvvmdemo.ui.main

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.xslczx.mvvmdemo.R
import com.xslczx.mvvmdemo.core.BaseVMFragment
import com.xslczx.mvvmdemo.ext.addOnItemClick
import com.xslczx.mvvmdemo.ext.closeAnim
import com.xslczx.mvvmdemo.ext.toast
import com.xslczx.mvvmdemo.model.adapter.AverageGridDecoration
import com.xslczx.mvvmdemo.model.adapter.WpAdapter
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
      addOnItemClick { position, _ ->
        requireContext().toast("pos=$position")
      }
    }
    refreshLayout.apply {
      setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
      setOnRefreshListener {
        mViewModel.reload()
      }
    }
  }

  override fun initData() {
    mViewModel.loadData(Bundle())
  }

  override fun startObserve() {
    super.startObserve()
    mViewModel.pagedListObservable
        .observe(viewLifecycleOwner, Observer {
          mAdapter.submitList(it)
        })
    mViewModel.itemFrontLiveData.observe(viewLifecycleOwner, Observer {
      it.getContentIfNotHandled()
          ?.let {
            refreshLayout.isRefreshing = false
          }
    })
    mViewModel.itemEndLiveData.observe(viewLifecycleOwner, Observer {
      it.getContentIfNotHandled()
          ?.let {
            requireContext().toast("加载完毕")
          }
    })
    mViewModel.itemZeroLiveData.observe(viewLifecycleOwner, Observer {
      it.getContentIfNotHandled()
          ?.let { msg ->
            requireContext().toast(msg)
          }
    })
  }
}