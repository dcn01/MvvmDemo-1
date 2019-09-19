package com.xslczx.mvvmdemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xslczx.mvvmdemo.core.BaseViewModel
import com.xslczx.mvvmdemo.model.bean.WpBean
import com.xslczx.mvvmdemo.model.paging.DataSourceFactory
import com.xslczx.mvvmdemo.model.repository.WpRepository

class MainViewModel : BaseViewModel() {
  private val repository by lazy { WpRepository() }
  private val factory by lazy { DataSourceFactory(this, repository) }
  private val config = PagedList.Config.Builder()
      .setPageSize(10)                         //配置分页加载的数量
      .setEnablePlaceholders(false)            //配置是否启动PlaceHolders
      .setInitialLoadSizeHint(10)              //初始化加载的数量
      .build()

  val refresh: MutableLiveData<Boolean> = MutableLiveData()
  val loadMore: MutableLiveData<Boolean> = MutableLiveData()

  fun newPagedList(): LiveData<PagedList<WpBean>> {
    return LivePagedListBuilder(
        factory, config
    ).build()
  }
}