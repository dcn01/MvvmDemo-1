package com.xslczx.mvvmdemo.model.paging

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.xslczx.mvvmdemo.core.BaseViewModel
import com.xslczx.mvvmdemo.model.bean.WpBean
import com.xslczx.mvvmdemo.model.repository.WpRepository

class PositionalDataSourceImpl(
  private val arg: Bundle,
  private val viewModel: BaseViewModel
) :
    PositionalDataSource<WpBean>() {

  private val wpRepository = WpRepository()

  override fun loadRange(
    params: LoadRangeParams,
    callback: LoadRangeCallback<WpBean>
  ) {
    viewModel.launch {
      val result = wpRepository.loadVerticalWp(arg, params.startPosition, params.loadSize)
      Log.i("logger", "loadRange===>${params.startPosition}/${params.loadSize}")
      callback.onResult(result.res.vertical)
    }
  }

  override fun loadInitial(
    params: LoadInitialParams,
    callback: LoadInitialCallback<WpBean>
  ) {
    viewModel.launch {
      val result = wpRepository.loadVerticalWp(arg, 0, params.pageSize)
      Log.i("logger", "loadInitial===>0/${params.pageSize}")
      callback.onResult(result.res.vertical, 0)
    }
  }
}

class SimpleFactory(
  private val arg: Bundle,
  private val viewModel: BaseViewModel
) : DataSource.Factory<Int, WpBean>() {

  private val mSourceLiveData = MutableLiveData<PositionalDataSourceImpl>()

  override fun create(): DataSource<Int, WpBean> {

    val dataSource = PositionalDataSourceImpl(arg, viewModel)
    mSourceLiveData.postValue(dataSource)
    return dataSource
  }

  fun getSourceLiveData(): MutableLiveData<PositionalDataSourceImpl> {
    return mSourceLiveData
  }

}

