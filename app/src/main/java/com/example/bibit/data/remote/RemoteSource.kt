package com.example.bibit.data.remote

internal interface RemoteSource {
    suspend fun requestNews(): ServiceResponse?
}