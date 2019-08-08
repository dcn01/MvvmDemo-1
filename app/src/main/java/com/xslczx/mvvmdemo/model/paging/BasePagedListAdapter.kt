package com.xslczx.mvvmdemo.model.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xslczx.mvvmdemo.model.base.BaseBean
import com.xslczx.mvvmdemo.model.paging.BasePagedListAdapter.MyViewHolder

abstract class BasePagedListAdapter<T : BaseBean>(@LayoutRes private val layoutId: Int) :
    PagedListAdapter<T, MyViewHolder>(
        MyDiffCallBack()
    ) {

  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MyViewHolder {
    val itemView = LayoutInflater.from(parent.context)
        .inflate(layoutId, parent, false)
    return MyViewHolder(itemView)
  }

  internal class MyDiffCallBack<B : BaseBean> : DiffUtil.ItemCallback<B>() {
    override fun areItemsTheSame(
      oldItem: B,
      newItem: B
    ): Boolean = oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(
      oldItem: B,
      newItem: B
    ): Boolean = oldItem.equals(newItem)
  }
}