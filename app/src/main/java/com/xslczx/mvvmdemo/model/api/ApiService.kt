package com.xslczx.mvvmdemo.model.api

import com.xslczx.mvvmdemo.model.base.BaseResult
import com.xslczx.mvvmdemo.model.bean.VerticalWp
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ApiService {

  companion object {
    const val BASE_URL = "http://service.picasso.adesk.com/"
  }

  @GET("/v1/vertical/vertical")
  @Headers("Content-Type: application/json;charset=UTF-8")
  suspend fun loadVerticalWallpaper(@QueryMap map: Map<String, String>): BaseResult<VerticalWp>
}