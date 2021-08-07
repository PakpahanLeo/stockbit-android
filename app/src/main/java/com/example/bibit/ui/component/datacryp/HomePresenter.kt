package com.example.bibit.ui.component.datacryp

import android.os.Bundle
import com.example.bibit.data.remote.model.Data
import com.example.bibit.data.remote.model.DataModel
import com.example.bibit.data.remote.model.SponsoredData
import com.example.bibit.ui.base.Presenter
import com.example.bibit.ui.base.listeners.BaseCallback
import com.example.bibit.ui.base.listeners.RecyclerItemListener
import com.example.bibit.usecase.DataCrypUseCase
import com.example.bibit.utils.ObjectUtil
import javax.inject.Inject

class HomePresenter @Inject
constructor(private val newsUseCase: DataCrypUseCase) : Presenter<HomeContract.View>(),
    HomeContract.Presenter, RecyclerItemListener {
    var newsModel: DataModel? = null

    override fun getRecyclerItemListener(): RecyclerItemListener {
        return this
    }

    override fun onItemSelected(position: Int) {
    }

    private val callback = object : BaseCallback {
        override fun onSuccess(newsModel: DataModel) {
            getView()?.decrementCountingIdlingResource()
            this@HomePresenter.newsModel = newsModel
            var newsItems: ArrayList<Data>? = null
            if (!ObjectUtil.isNull(newsModel)) {
                newsItems = newsModel.data
            }
            if (!ObjectUtil.isNull(newsItems) && !newsItems!!.isEmpty()) {
                getView()?.initializeDataList(newsModel.data!!)
                showList(true)
            } else {
                showList(false)
            }
            getView()?.setLoaderVisibility(false)
        }

        override fun onFail() {
            getView()?.decrementCountingIdlingResource()
            showList(false)
            getView()?.setLoaderVisibility(false)
        }
    }

    override fun initialize(extras: Bundle?) {
        super.initialize(extras)
        getData()
    }

    override fun getData() {
        getView()?.setLoaderVisibility(true)
        getView()?.setNoDataVisibility(false)
        getView()?.setListVisibility(false)
        getView()?.incrementCountingIdlingResource()
        newsUseCase.getNews(callback)
    }

    override fun unSubscribe() {
    }

    private fun showList(isVisible: Boolean) {
        getView()?.setNoDataVisibility(!isVisible)
        getView()?.setListVisibility(isVisible)
    }
}
