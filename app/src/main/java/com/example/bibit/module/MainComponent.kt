package com.example.bibit.module

import com.example.bibit.ui.component.auth.LoginActivity
//import com.example.bibit.ui.component.datacryp.HomeActivity
import com.example.bibit.ui.component.frame.activity.NavigationActivity
import com.example.bibit.ui.component.frame.fragment.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(fragment: HomeFragment)
    fun inject(activity: NavigationActivity)
//    fun inject(activity: HomeActivity)
    fun inject(activity: LoginActivity)
}