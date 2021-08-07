package com.example.bibit.ui.component.datacryp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bibit.data.remote.model.Data
import com.example.bibit.ui.base.listeners.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer

class LoadDataViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    fun bind(position: Int, newsItem: Data, recyclerItemListener: RecyclerItemListener) {

    }
}