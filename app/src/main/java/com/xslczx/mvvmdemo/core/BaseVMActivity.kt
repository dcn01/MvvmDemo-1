package com.xslczx.mvvmdemo.core

import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity(), LifecycleObserver {

  protected val mViewModel: VM  by lazy {
    ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        .create(providerVMClass())
        .apply {
          mViewModel.let(lifecycle::addObserver)
        }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    startObserve()
  }

  abstract fun providerVMClass(): Class<VM>

  open fun startObserve() {
    mViewModel.mException.observe(this, Observer { it?.let { onError(it) } })
  }

  open fun onError(e: Throwable) {}

  override fun onDestroy() {
    mViewModel.let {
      lifecycle.removeObserver(it)
    }
    super.onDestroy()
  }
}