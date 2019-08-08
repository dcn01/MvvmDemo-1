package com.xslczx.mvvmdemo.model.paging

import androidx.paging.DataSource
import com.xslczx.mvvmdemo.model.bean.WpBean
import com.xslczx.mvvmdemo.model.repository.WpRepository
import com.xslczx.mvvmdemo.ui.main.MainViewModel

class AmerDataSourceFactory(private val viewModel: MainViewModel, private val repository: WpRepository) :
    DataSource.Factory<Int, WpBean>() {
    override fun create(): DataSource<Int, WpBean> {
        return PositionalDataSourceImpl(viewModel, repository)
    }
}
