package com.example.bibit.ui.component.frame.activity

import com.example.bibit.ui.base.listeners.BaseView

interface NavigateContract {
    interface View : BaseView {
        fun InitMainScreen()
    }

    interface Presenter
}