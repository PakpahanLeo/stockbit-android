package com.example.bibit.ui.component.frame.activity

import android.os.Bundle
import com.example.bibit.ui.base.Presenter
import javax.inject.Inject

class NavigatePresenter@Inject
constructor() : Presenter<NavigateContract.View>(), NavigateContract.Presenter {

    override fun initialize(extras: Bundle?) {
        super.initialize(extras)
        getView()?.InitMainScreen()
    }
}