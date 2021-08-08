package com.example.bibit


import android.content.Intent
import org.hamcrest.Matchers

import org.junit.After

import org.junit.Before

import org.junit.Rule

import org.junit.Test

import org.junit.runner.RunWith


import androidx.test.espresso.Espresso

import androidx.test.espresso.IdlingResource

import androidx.test.espresso.ViewInteraction

import androidx.test.espresso.action.ViewActions

import androidx.test.espresso.contrib.RecyclerViewActions

import androidx.test.espresso.matcher.ViewMatchers

import androidx.test.rule.ActivityTestRule

import androidx.test.runner.AndroidJUnit4


import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.Espresso.pressBack

import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.action.ViewActions.pressImeActionButton

import androidx.test.espresso.action.ViewActions.scrollTo

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.platform.app.InstrumentationRegistry
import com.example.bibit.ui.component.auth.LoginActivity
import com.example.bibit.ui.component.frame.activity.NavigationActivity

import org.hamcrest.Matchers.not


import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BibitInstrumentedTest {

}