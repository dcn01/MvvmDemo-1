package com.xslczx.mvvmdemo.model.paging

import android.util.Log
import androidx.paging.PositionalDataSource
import com.xslczx.mvvmdemo.model.bean.WpBean
import com.xslczx.mvvmdemo.model.repository.WpRepository
import com.xslczx.mvvmdemo.ui.main.MainViewModel

class PositionalDataSourceImpl(
  private val viewModel: MainViewModel,
  private val wpRepository: WpRepository
) :
    PositionalDataSource<WpBean>() {

  override fun loadRange(
    params: LoadRangeParams,
    callback: LoadRangeCallback<WpBean>
  ) {
    viewModel.launch {
      viewModel.loadMore.value = true
      val result = wpRepository.loadVerticalWp(params.startPosition, params.loadSize)
      Log.i("logger", "loadRange===>${params.startPosition}/${params.loadSize}")
      callback.onResult(result.res.vertical)
      viewModel.loadMore.value = false
    }
  }

  override fun loadInitial(
    params: LoadInitialParams,
    callback: LoadInitialCallback<WpBean>
  ) {
    viewModel.launch {
      viewModel.refresh.value = true
      val result = wpRepository.loadVerticalWp(0, params.pageSize)
      Log.i("logger", "loadInitial===>0/${params.pageSize}")
      callback.onResult(result.res.vertical, 0)
      viewModel.refresh.value = false
    }
  }
}