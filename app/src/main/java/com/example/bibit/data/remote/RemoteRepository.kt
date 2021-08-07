package com.example.bibit.data.remote

import com.example.bibit.App
import com.example.bibit.data.remote.service.DataService
import com.example.bibit.utils.Constants
import com.example.bibit.utils.Constants.INSTANCE.ERROR_UNDEFINED
import com.example.bibit.utils.Network.Utils.isConnected
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator) : RemoteSource {

    override suspend fun requestNews(): ServiceResponse? {
        return if (!isConnected(App.context)) {
            ServiceResponse(ServiceError(code = -1, description = ServiceError.NETWORK_ERROR))
        } else {
            val newsService = serviceGenerator.createService(DataService::class.java, Constants.BASE_URL)
            processCall(newsService.fetchData(), false)
        }
    }

    private fun processCall(call: Call<*>, isVoid: Boolean): ServiceResponse {
        if (!isConnected(App.context)) {
            return ServiceResponse(ServiceError())
        }
        try {
            val response = call.execute()
                ?: return ServiceResponse(ServiceError(ServiceError.NETWORK_ERROR, ERROR_UNDEFINED))
            val responseCode = response.code()
            /**
             * isVoid is for APIs which reply only with code without any body, such as some Apis
             * reply with 200 or 401....
             */
            return if (response.isSuccessful) {
                val apiResponse: Any? = if (isVoid) null else response.body()
                ServiceResponse(responseCode, apiResponse)
            } else {
                val serviceError = ServiceError(response.message(), responseCode)
                ServiceResponse(serviceError)
            }
        } catch (e: IOException) {
            return ServiceResponse(ServiceError(ServiceError.NETWORK_ERROR, ERROR_UNDEFINED))
        }

    }
}