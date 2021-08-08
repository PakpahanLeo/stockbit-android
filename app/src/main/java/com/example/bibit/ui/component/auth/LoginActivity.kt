package com.example.bibit.ui.component.auth

import android.os.Handler
import com.example.bibit.R
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.test.espresso.IdlingResource
import com.example.bibit.App
import com.example.bibit.ui.base.BaseActivity
import com.example.bibit.ui.component.frame.activity.NavigationActivity
import com.example.bibit.utils.Constants
import com.example.bibit.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class LoginActivity : BaseActivity(), AuthContract.View {
    companion object {
        var isPasswordVisible = true
    }

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    @Inject
    lateinit var authPresenter: AuthPresenter

    override val layoutId: Int
        get() = R.layout.activity_login

    override fun initializeDagger() {
        val app = applicationContext as App
        app.mainComponent?.inject(this@LoginActivity)
    }

    override fun initializePresenter() {
        authPresenter.setView(this)
        super.presenter = authPresenter
    }

    //for testing
    fun validate(userName: String, password: String): String? {
        return if (userName == "userbibit" && password == "passwordbibit") "Login berhasil" else "Gagal login!"
    }

    override fun NavigateToMainScreen() {
        buttonLogin.setOnClickListener {
            Handler().postDelayed({
                startActivity<NavigationActivity>()
                finish()
            }, Constants.SPLASH_DELAY.toLong())
        }
        editTextPassword.setOnTouchListener(object : View.OnTouchListener {
            override
            fun onTouch(v: View?, event: MotionEvent): Boolean {
                val RIGHT = 2
                if (event.action == MotionEvent.ACTION_UP) {
                    if (event.rawX >= editTextPassword.getRight() - editTextPassword.getCompoundDrawables()
                            .get(RIGHT).getBounds().width()
                    ) {
                        val selection: Int = editTextPassword.getSelectionEnd()
                        if (isPasswordVisible) {
                            editTextPassword.setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.ic_invisible,
                                0
                            )
                            editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                            isPasswordVisible = false
                        } else {
                            editTextPassword.setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.ic_visible,
                                0
                            )
                            editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                            isPasswordVisible = true
                        }
                        editTextPassword.setSelection(selection)
                        return true
                    }
                }
                return false
            }
        })
    }
}