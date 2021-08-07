package com.example.bibit.data.remote.service

import com.example.bibit.data.remote.model.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface DataService {
    @GET("data/top/totaltoptiervolfull?limit=50&tsym=USD")
    fun fetchData(): Call<DataModel>
}