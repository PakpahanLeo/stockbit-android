package com.example.bibit.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.example.bibit.ui.base.listeners.BaseView

abstract class BaseFragment : Fragment(), BaseView {

    protected var presenter: Presenter<*>? = null

    abstract val layoutId: Int

    private val toolbarTitleKey: String? = null

    protected abstract fun initializeDagger()

    protected abstract fun initializePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDagger()
        initializePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        ButterKnife.bind(this, view)
        presenter?.initialize(arguments)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter?.start()
    }

    override fun onStop() {
        super.onStop()
        presenter?.finalizeView()
    }

}