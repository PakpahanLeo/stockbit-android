package com.example.bibit.ui.component.datacryp

import android.content.ContentValues.TAG
import android.os.Handler
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.IdlingResource
import com.example.bibit.App
import com.example.bibit.R
import com.example.bibit.data.remote.model.Data
import com.example.bibit.data.remote.model.SponsoredData
import com.example.bibit.ui.base.BaseActivity
import com.example.bibit.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

import android.graphics.Movie
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.bibit.data.remote.model.CoinInfo
class HomeActivity : BaseActivity(), HomeContract.View {
    @Inject
    lateinit var homePresenter: HomePresenter

    private val PAGE_START = 0
    private var isLoading = false
    private var isLastPage = false
    private val TOTAL_PAGES = 5
    private var currentPage = PAGE_START
    var itemCount = 0
    var imol: ArrayList<Data> = ArrayList()

    override val layoutId: Int
        get() = R.layout.activity_home

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    override fun initializeDagger() {
        val app = applicationContext as App
        app.mainComponent?.inject(this@HomeActivity)
    }

    override fun initializePresenter() {
        homePresenter.setView(this)
        super.presenter = homePresenter

    }

    override fun initializeSponsoredList(news: List<SponsoredData>) {
//        TODO("Not yet implemented")
    }

    override fun initializeDataList(news: ArrayList<Data>) {
        imol = news
//        val newsAdapter = PaginationAdapter(this)
        val newsAdapter = HomeAdapter(homePresenter.getRecyclerItemListener(), news)
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        rv_news_list.layoutManager = layoutManager
        rv_news_list.setItemAnimator(DefaultItemAnimator())
        rv_news_list.adapter = newsAdapter

//        rv_news_list.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
//            override fun loadMoreItems() {
//                println("PRINT DISINI")
//                isLoading = true
//                currentPage += 1
//
//                // mocking network delay for API call
//                Handler().postDelayed({
//                    Log.d(TAG, "loadNextPage: " + currentPage);
//
//
//                    val movies: ArrayList<Data> = createMovies(newsAdapter.itemCount)
//                    newsAdapter.removeLoadingFooter()
//                    isLoading = false
//                    newsAdapter.addAll(movies)
//                    if (currentPage !== TOTAL_PAGES) newsAdapter.addLoadingFooter() else isLastPage = true
//                }, 1000)
//            }
//
//            override fun getTotalPageCount(): Int {
//                return TOTAL_PAGES
//            }
//
//            override fun isLastPage(): Boolean {
//                return isLastPage
//            }
//
//            override fun isLoading(): Boolean {
//                return isLoading
//            }
//
//
////            override var totalPageCount: Int
////                get() = TOTAL_PAGES
////            override val isLastPage: Boolean
////                get() = isLastPage
////            override var isLoading: Boolean = false
////                get() = isLoading
//
//        })


//        Handler().postDelayed({
//            Log.d(TAG, "loadFirstPage: ");
//            val movies: ArrayList<Data> = createMovies(newsAdapter.itemCount)
//
//
//
//            pb_loading.setVisibility(View.GONE)
//            newsAdapter.addAll(movies)
//            if (currentPage <= TOTAL_PAGES) newsAdapter.addLoadingFooter() else isLastPage = true
//        }, 1000)

//        rv_news_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
//                if (!isLoading) {
//                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == news.size - 1) {
//                        //bottom of list!
////                        news.addAll(null!!)
//                        newsAdapter.notifyItemInserted(news.size - 1)
//                        val handler = Handler()
//                        handler.postDelayed(Runnable {
//                            news.removeAt(news.size - 1)
//                            val scrollPosition: Int = news.size
//                            newsAdapter.notifyItemRemoved(scrollPosition)
//                            var currentSize = scrollPosition
//                            val nextLimit = currentSize + 10
//                            while (currentSize - 1 < nextLimit) {
//                                news.addAll(news)
//                                currentSize++
//                            }
//                            newsAdapter.notifyDataSetChanged()
//                            isLoading = false
//                        }, 2000)
//                        isLoading = true
//                    }
//                }
//            }
//        })

        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = true
            homePresenter.getData()
        }
    }
//    fun createMovies(itemCount: Int): ArrayList<Data> {
//        val movies: ArrayList<Data> = ArrayList()
//        for (i in 0..9) {
//            val movie = Data(imol[i].coinInfo)
//            movies.add(movie)
//        }
//        return movies
//    }

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

//    @OnClick(R.id.ic_toolbar_setting, R.id.ic_toolbar_refresh, R.id.btn_search)
//    fun onClick(view: View) {
//        when (view.id) {
////            R.id.ic_toolbar_refresh -> homePresenter.getNews()
////            R.id.btn_search -> homePresenter.onSearchClick(et_search.text.toString())
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        homePresenter.unSubscribe()
    }


}