package com.xslczx.mvvmdemo.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMFragment<VM : BaseViewModel> : androidx.fragment.app.Fragment() {

  protected val mViewModel: VM by lazy {
    ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        .create(providerVMClass()).apply {
          lifecycle.addObserver(mViewModel)
        }
  }

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
    initData()
    startObserve()
    super.onViewCreated(view, savedInstanceState)
  }

  open fun startObserve() {
    mViewModel.mException.observe(this, Observer { it?.let { onError(it) } })
  }

  open fun onError(e: Throwable) {}

  abstract fun getLayoutResId(): Int

  abstract fun initView()

  abstract fun initData()

  abstract fun providerVMClass(): Class<VM>

  override fun onDestroy() {
    lifecycle.removeObserver(mViewModel)
    super.onDestroy()
  }
}