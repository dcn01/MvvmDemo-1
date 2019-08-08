package com.xslczx.mvvmdemo.model.adapter

import com.bumptech.glide.Glide
import com.xslczx.mvvmdemo.R
import com.xslczx.mvvmdemo.model.bean.WpBean
import com.xslczx.mvvmdemo.model.paging.BasePagedListAdapter
import kotlinx.android.synthetic.main.item_layout.view.item_img

class WpAdapter :
    BasePagedListAdapter<WpBean>(R.layout.item_layout) {

  override fun onBindViewHolder(
    holder: MyViewHolder,
    position: Int
  ) {
    val item = getItem(position) ?: return
    Glide.with(holder.itemView.item_img)
        .load(item.thumb)
        .into(holder.itemView.item_img)
  }

}