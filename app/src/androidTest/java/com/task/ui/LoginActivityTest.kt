package com.task.ui

import androidx.test.rule.ActivityTestRule
import com.example.bibit.ui.component.auth.LoginActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginActivityTest {

    @get:Rule
    var mActivityTestRule: ActivityTestRule<LoginActivity> =
        ActivityTestRule(LoginActivity::class.java)
    var loginActivity: LoginActivity? = null
    private val RESPONSE_STRING = "Login berhasil"

    @Before
    fun setUp() {
        loginActivity = mActivityTestRule.activity
    }

    @Test
    fun testLaunch() {
        val result: String = loginActivity?.validate("userbibit", "passwordbibit")!!
        ViewMatchers.assertThat(result, CoreMatchers.`is`(RESPONSE_STRING))
        ViewMatchers.isChecked()
    }

    @After
    fun tearDown() {
        loginActivity = null
    }
}