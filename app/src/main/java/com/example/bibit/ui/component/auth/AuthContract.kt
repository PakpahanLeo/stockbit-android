package com.example.bibit.ui.component.auth

import com.example.bibit.ui.base.listeners.BaseView

interface AuthContract {
    interface View : BaseView {
        fun NavigateToMainScreen()
    }

    interface Presenter
}