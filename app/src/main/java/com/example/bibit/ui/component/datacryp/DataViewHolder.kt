package com.example.bibit.ui.component.datacryp

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bibit.data.remote.model.Data
import com.example.bibit.ui.base.listeners.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.data_item.*

class DataViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(position: Int, newsItem: Data, recyclerItemListener: RecyclerItemListener) {
        tv_name.text = newsItem.coinInfo?.name ?: ""
        tv_fullname.text = newsItem.coinInfo?.fullName ?: ""
        tv_price.text = newsItem.raw?.usd?.price.toString() ?: ""
        if (newsItem.raw?.usd?.flags.toString().equals("2052")) {
            tv_percentage.setTextColor(Color.parseColor("#0BAE71"))
        } else if (newsItem.raw?.usd?.flags.toString().equals("1026")) {
            tv_percentage.setTextColor(Color.parseColor("#F06968"))
        }
        tv_percentage.text = newsItem.raw?.usd?.highDay.toString() ?: ""
        rl_news_item.setOnClickListener { recyclerItemListener.onItemSelected(position) }
    }
}