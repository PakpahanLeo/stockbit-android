package com.example.bibit.ui.component.datacryp

import com.example.bibit.data.remote.model.Data
import com.example.bibit.data.remote.model.SponsoredData
import com.example.bibit.ui.base.listeners.BaseView
import com.example.bibit.ui.base.listeners.RecyclerItemListener

interface HomeContract {
    interface View : BaseView {
        fun initializeSponsoredList(news: List<SponsoredData>)
        fun initializeDataList(news: ArrayList<Data>)
        fun setLoaderVisibility(isVisible: Boolean)
        fun setNoDataVisibility(isVisible: Boolean)
        fun setListVisibility(isVisible: Boolean)
        fun showSearchError()
        fun showNoNewsError()
        fun incrementCountingIdlingResource()
        fun decrementCountingIdlingResource()
    }
    interface Presenter {
        fun getRecyclerItemListener(): RecyclerItemListener
        fun getData()
        fun unSubscribe()
    }
}