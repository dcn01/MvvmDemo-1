package com.xslczx.mvvmdemo.ui.main

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xslczx.mvvmdemo.core.BaseViewModel
import com.xslczx.mvvmdemo.model.bean.WpBean
import com.xslczx.mvvmdemo.model.paging.AmerDataSourceFactory
import com.xslczx.mvvmdemo.model.repository.WpRepository

class MainViewModel : BaseViewModel() {
  private val repository by lazy { WpRepository() }
  private val factory by lazy { AmerDataSourceFactory(this, repository) }
  private val config = PagedList.Config.Builder()
      .setPageSize(10)                         //配置分页加载的数量
      .setEnablePlaceholders(false)            //配置是否启动PlaceHolders
      .setInitialLoadSizeHint(0)              //初始化加载的数量
      .build()

  val liveData: LiveData<PagedList<WpBean>> = LivePagedListBuilder(
      factory, config
  ).build()
}