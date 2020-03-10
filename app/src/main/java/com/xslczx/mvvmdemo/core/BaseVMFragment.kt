package com.xslczx.mvvmdemo.core

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {

  protected lateinit var mViewModel: VM

  override fun onAttach(context: Context) {
    super.onAttach(context)
    mViewModel =
      ViewModelProvider.NewInstanceFactory()
          .create(providerVMClass())
          .also {
            it.let(lifecycle::addObserver)
          }
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    startObserve()
  }

  open fun startObserve() {
    mViewModel.mException.observe(viewLifecycleOwner, Observer { it?.let { onError(it) } })
  }

  open fun onError(e: Throwable) {}

  abstract fun providerVMClass(): Class<VM>

  override fun onDestroy() {
    lifecycle.removeObserver(mViewModel)
    super.onDestroy()
  }
}