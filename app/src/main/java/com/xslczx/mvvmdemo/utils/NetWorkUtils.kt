package com.xslczx.mvvmdemo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES

object NetWorkUtils {

  /**
   * 网络是否已连接
   *
   * @return true:已连接 false:未连接
   */
  @SuppressLint("MissingPermission")
  @JvmStatic
  fun iConnected(context: Context): Boolean {
    val connectivityManager = context.applicationContext
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    return if (connectivityManager != null) {
      if (VERSION.SDK_INT >= VERSION_CODES.M) {
        val networkCapabilities =
          connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        networkCapabilities != null &&
            (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
      } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo != null && networkInfo.isConnected
      }
    } else false
  }

  /**
   * Wifi是否已连接
   *
   * @return true:已连接 false:未连接
   */
  @SuppressLint("MissingPermission")
  @JvmStatic
  fun isWifiConnected(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    if (manager != null) {
      if (VERSION.SDK_INT >= VERSION_CODES.M) {
        val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
        if (networkCapabilities != null) {
          return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
      } else {
        val networkInfo = manager.activeNetworkInfo
        return (networkInfo != null && networkInfo.isConnected
            && networkInfo.type == ConnectivityManager.TYPE_WIFI)
      }
    }
    return false
  }

  /**
   * 是否为流量
   */
  @SuppressLint("MissingPermission")
  @JvmStatic
  fun isMobileData(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    if (manager != null) {
      if (VERSION.SDK_INT >= VERSION_CODES.M) {
        val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
        if (networkCapabilities != null) {
          return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        }
      } else {
        val networkInfo = manager.activeNetworkInfo
        return (networkInfo != null && networkInfo.isConnected
            && networkInfo.type == ConnectivityManager.TYPE_MOBILE)
      }
    }
    return false
  }
}