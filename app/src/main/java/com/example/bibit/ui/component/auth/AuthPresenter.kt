package com.example.bibit.ui.component.auth

import android.os.Bundle
import com.example.bibit.ui.base.Presenter
import javax.inject.Inject

class AuthPresenter @Inject
constructor() : Presenter<AuthContract.View>(), AuthContract.Presenter {

    override fun initialize(extras: Bundle?) {
        super.initialize(extras)
        getView()?.NavigateToMainScreen()
    }
}
