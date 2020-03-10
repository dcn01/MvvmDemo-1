package com.xslczx.mvvmdemo.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xslczx.mvvmdemo.core.BaseViewModel
import com.xslczx.mvvmdemo.model.bean.Event
import com.xslczx.mvvmdemo.model.bean.WpBean
import com.xslczx.mvvmdemo.model.paging.PositionalDataSourceImpl
import com.xslczx.mvvmdemo.model.paging.SimpleFactory

class MainViewModel : BaseViewModel() {
  private val _argLiveData = MutableLiveData<Bundle>()

  private val _config = PagedList.Config.Builder()
      .setEnablePlaceholders(false)
      .setInitialLoadSizeHint(20)
      .setPageSize(20)
      .setPrefetchDistance(10)
      .build()

  private val _factoryObservable = MutableLiveData<SimpleFactory>()

  /**
   * 最后一条数据加载
   */
  val itemEndLiveData: LiveData<Event<WpBean>> get() = _end
  private val _end = MutableLiveData<Event<WpBean>>()

  /**
   * 第一条数据加载
   */
  val itemFrontLiveData: LiveData<Event<WpBean>> get() = _front
  private val _front = MutableLiveData<Event<WpBean>>()

  /**
   * 无数据加载
   */
  val itemZeroLiveData: LiveData<Event<String?>> get() = _zero
  private val _zero = MutableLiveData<Event<String?>>()

  val pagedListObservable = Transformations.switchMap(_argLiveData) {
    val factory = SimpleFactory(it, this)
    _factoryObservable.postValue(factory)
    LivePagedListBuilder(factory, _config)
        .setBoundaryCallback(object : PagedList.BoundaryCallback<WpBean>() {
          override fun onItemAtEndLoaded(itemAtEnd: WpBean) {
            super.onItemAtEndLoaded(itemAtEnd)
            _end.postValue(Event((itemAtEnd)))
          }

          override fun onItemAtFrontLoaded(itemAtFront: WpBean) {
            super.onItemAtFrontLoaded(itemAtFront)
            _front.postValue(Event(itemAtFront))
          }

          override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
            _zero.postValue(Event("暂无数据"))
          }
        })
        .build()
  }

  private fun getDataSource(): PositionalDataSourceImpl? {
    var dataSource: PositionalDataSourceImpl? = null
    val factory: SimpleFactory? = _factoryObservable.value
    factory?.let {
      dataSource = it.getSourceLiveData().value
    }
    return dataSource
  }

  fun loadData(arguments: Bundle) {
    _argLiveData.value = arguments
  }

  /**
   * 重置数据，类似刷新效果
   */
  fun reload() {
    getDataSource()?.invalidate()
  }

}