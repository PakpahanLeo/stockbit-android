package com.example.bibit.ui.component.datacryp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bibit.R
import com.example.bibit.data.remote.model.Data
import com.example.bibit.ui.base.listeners.RecyclerItemListener

class HomeAdapter(
    private val onItemClickListener: RecyclerItemListener,
    private val news: List<Data>
) : RecyclerView.Adapter<DataViewHolder>() {

    private val ITEM = 0
    private val LOADING = 1

    private val isLoadingAdded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(position, news[position], onItemClickListener)
    }
    override fun getItemViewType(position: Int): Int {
        return if (position == news.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    override fun getItemCount(): Int {
        return if (news == null) 0 else news.size
    }
}