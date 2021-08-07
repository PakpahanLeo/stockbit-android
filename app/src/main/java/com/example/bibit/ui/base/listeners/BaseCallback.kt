package com.example.bibit.ui.base.listeners

import com.example.bibit.data.remote.model.DataModel

interface BaseCallback {
    fun onSuccess(newsModel: DataModel)
    fun onFail()
}