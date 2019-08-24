package com.xslczx.mvvmdemo.model.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
        .apply(RequestOptions().placeholder(ColorDrawable(Color.parseColor("#f2f2f2"))))
        .into(holder.itemView.item_img)
  }

}