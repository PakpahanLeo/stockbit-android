package com.example.bibit.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.intent.Checks
import java.util.concurrent.atomic.AtomicInteger

class SimpleCountingIdlingResource
    (resourceName: String) : IdlingResource {
    private val mResourceName: String = Checks.checkNotNull(resourceName)
    private val counter = AtomicInteger(0)

    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return mResourceName
    }

    override fun isIdleNow(): Boolean {
        return counter.get() == 0
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if (counterVal == 0) {
            if (null != resourceCallback) {
                resourceCallback!!.onTransitionToIdle()
            }
        }

        if (counterVal < 0) {
            throw IllegalArgumentException("Counter has been corrupted!")
        }
    }
}