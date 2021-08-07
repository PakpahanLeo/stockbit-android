package com.example.bibit.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.bibit.ui.base.listeners.ActionBarView
import com.example.bibit.ui.base.listeners.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView, ActionBarView {
    protected lateinit var presenter: Presenter<*>
    private var unbinder: Unbinder? = null
    abstract val layoutId: Int
    protected abstract fun initializeDagger()
    protected abstract fun initializePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        unbinder = ButterKnife.bind(this)
        initializeDagger()
        initializePresenter()
        presenter.initialize(intent.extras)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.finalizeView()
    }


    override fun setUpIconVisibility(visible: Boolean) {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(visible)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder?.unbind()
    }
}