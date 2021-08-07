package com.example.bibit.ui.component.frame.activity

import androidx.fragment.app.Fragment
import com.example.bibit.App
import com.example.bibit.R
import com.example.bibit.ui.base.BaseActivity
import com.example.bibit.ui.component.frame.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import javax.inject.Inject

class NavigationActivity : BaseActivity(), NavigateContract.View {
    override val layoutId: Int
        get() = R.layout.activity_navigation

    @Inject
    lateinit var navigatePresenter: NavigatePresenter

    override fun initializeDagger() {
        val app = applicationContext as App
        app.mainComponent?.inject(this@NavigationActivity)
    }

    override fun initializePresenter() {
        navigatePresenter.setView(this)
        super.presenter = navigatePresenter
    }

    override fun InitMainScreen() {
        val firstFragment = HomeFragment()
        setCurrentFragment(firstFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(firstFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}