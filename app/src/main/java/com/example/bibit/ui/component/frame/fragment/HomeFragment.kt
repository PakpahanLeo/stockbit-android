package com.example.bibit.ui.component.frame.fragment

import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.IdlingResource
import butterknife.BindView
import com.example.bibit.App
import com.example.bibit.R
import com.example.bibit.data.remote.model.Data
import com.example.bibit.data.remote.model.SponsoredData
import com.example.bibit.ui.base.BaseFragment
import com.example.bibit.ui.component.datacryp.HomeAdapter
import com.example.bibit.ui.component.datacryp.HomeContract
import com.example.bibit.ui.component.datacryp.HomePresenter
import com.example.bibit.utils.EspressoIdlingResource
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeContract.View {
    @Inject
    lateinit var homePresenter: HomePresenter

    @BindView(R.id.pb_loading)
    lateinit var pb_loading: ProgressBar

    @BindView(R.id.swiperefresh)
    lateinit var swiperefresh: SwipeRefreshLayout

    @BindView(R.id.tv_no_data)
    lateinit var tv_no_data: TextView

    @BindView(R.id.rl_news_list)
    lateinit var rl_news_list: RelativeLayout

    @BindView(R.id.rv_news_list)
    lateinit var rv_news_list: RecyclerView

    private val PAGE_START = 0
    private var isLoading = false
    private var isLastPage = false
    private val TOTAL_PAGES = 5
    private var currentPage = PAGE_START
    var itemCount = 0
    var imol: ArrayList<Data> = ArrayList()

    override val layoutId: Int
        get() = R.layout.fragment_home

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    override fun initializeDagger() {
        val app = context?.applicationContext as App
        app.mainComponent?.inject(this@HomeFragment)
    }

    override fun initializePresenter() {
        homePresenter.setView(this)
        super.presenter = homePresenter
    }

    override fun initializeSponsoredList(news: List<SponsoredData>) {
        //empty
//        TODO("Not yet implemented")
    }

    override fun initializeDataList(news: ArrayList<Data>) {
        imol = news
        val newsAdapter = HomeAdapter(homePresenter.getRecyclerItemListener(), news)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_news_list.layoutManager = layoutManager
        rv_news_list.setItemAnimator(DefaultItemAnimator())
        rv_news_list.adapter = newsAdapter

        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = true
            homePresenter.getData()
        }
    }

    override fun setLoaderVisibility(isVisible: Boolean) {
        pb_loading.visibility = if (isVisible) View.VISIBLE else View.GONE
        swiperefresh.isRefreshing = false
    }

    override fun setNoDataVisibility(isVisible: Boolean) {
        tv_no_data.visibility = if (isVisible) View.VISIBLE else View.GONE
        swiperefresh.isRefreshing = false
    }

    override fun setListVisibility(isVisible: Boolean) {
        rl_news_list.visibility = if (isVisible) View.VISIBLE else View.GONE
        swiperefresh.isRefreshing = false
    }

    override fun showSearchError() {
    }

    override fun showNoNewsError() {
        rl_news_list.snackbar(R.string.data_error)
    }

    override fun incrementCountingIdlingResource() {
        EspressoIdlingResource.increment()
    }

    override fun decrementCountingIdlingResource() {
        EspressoIdlingResource.decrement()
    }

    override fun onDestroy() {
        super.onDestroy()
        homePresenter.unSubscribe()
    }

}