package com.example.bibit.ui.component.datacryp

import androidx.recyclerview.widget.RecyclerView

import com.example.bibit.R
import android.content.Context

import android.widget.TextView


import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import com.example.bibit.data.remote.model.Data
import android.widget.ProgressBar


class PaginationAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var movies: MutableList<Data>?
    private val context: Context
    private var isLoadingAdded = false
    fun getMovies(): List<Data>? {
        return movies
    }

    fun setMovies(movies: MutableList<Data>?) {
        this.movies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            ITEM -> {
                val viewItem: View =
                    inflater.inflate(com.example.bibit.R.layout.data_item, parent, false)
                viewHolder = MovieVH(viewItem)

            }
            LOADING -> {
                val v2: View =
                    inflater.inflate(com.example.bibit.R.layout.item_progress, parent, false)
                viewHolder = LoadingVH(v2)
            }
        }
        return viewHolder!!
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = movies!![position]
        when (getItemViewType(position)) {
            ITEM -> {
                val movieVH = holder as MovieVH?
                movieVH!!.textView.setText(movie.coinInfo?.fullName)
            }
            LOADING -> {
//                val loadingVH = holder as LoadingVH
//                loadingVH!!.mProgressBar.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return if (movies == null) 0 else movies!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == movies!!.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    /*
   Helpers
   _________________________________________________________________________________________________
    */
    fun add(mc: Data) {
        movies!!.add(mc)
        notifyItemInserted(movies!!.size - 1)
    }

    fun addAll(mcList: List<Data>) {
        for (mc in mcList) {
            add(mc)
        }
    }

    fun remove(city: Data) {
        val position = movies!!.indexOf(city)
        if (position > -1) {
            movies!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    val isEmpty: Boolean
        get() = itemCount == 0

    fun addLoadingFooter() {
        isLoadingAdded = true
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position = movies!!.size - 1
        val item = getItem(position)
        if (item != null) {
            movies!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItem(position: Int): Data {
        return movies!![position]
    }
    /*
   View Holders
   _________________________________________________________________________________________________
    */
    /**
     * Main list's content ViewHolder
     */
    protected inner class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById(R.id.tv_name)
        }
    }

    protected inner class LoadingVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mProgressBar: ProgressBar

        init {
            mProgressBar = itemView.findViewById(R.id.loadmore_progress)
        }
    }


    companion object {
        private const val ITEM = 0
        private const val LOADING = 1
    }

    init {
        this.context = context
        movies = ArrayList()
    }

}