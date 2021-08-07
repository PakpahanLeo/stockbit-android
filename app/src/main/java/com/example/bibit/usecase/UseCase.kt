package com.example.bibit.usecase

import com.example.bibit.ui.base.listeners.BaseCallback

interface UseCase {
    fun getNews(callback: BaseCallback)
}