package com.example.bibit.data

import com.example.bibit.data.remote.ServiceResponse

internal interface DataSource {
    suspend fun requestNews(): ServiceResponse?
}