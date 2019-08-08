package com.xslczx.mvvmdemo.model.repository

import com.xslczx.mvvmdemo.model.api.ApiRetrofit
import com.xslczx.mvvmdemo.model.base.BaseRepository
import com.xslczx.mvvmdemo.model.base.BaseResult
import com.xslczx.mvvmdemo.model.bean.VerticalWp

class WpRepository : BaseRepository() {

  suspend fun loadVerticalWp(
    skip: Int,
    limit: Int
  ): BaseResult<VerticalWp> {
    return apiCall {
      ApiRetrofit.service.loadVerticalWallpaper(hashMapOf<String, String>().also {
        it["skip"] = skip.toString()
        it["limit"] = limit.toString()
      })
    }
  }
}