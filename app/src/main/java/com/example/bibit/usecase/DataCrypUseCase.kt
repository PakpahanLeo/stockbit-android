package com.example.bibit.usecase

import com.example.bibit.data.DataRepository
import com.example.bibit.data.remote.ServiceError
import com.example.bibit.data.remote.model.DataModel
import com.example.bibit.ui.base.listeners.BaseCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataCrypUseCase @Inject
constructor(
    private val dataRepository: DataRepository,
    override val coroutineContext: CoroutineContext
) : UseCase,
    CoroutineScope {

    override fun getNews(callback: BaseCallback) {
        launch {
            try {
                val serviceResponse = async(Dispatchers.IO) { dataRepository.requestNews() }.await()
                if (serviceResponse?.code == ServiceError.SUCCESS_CODE) {
                    val newsModel = serviceResponse.data as DataModel
                    callback.onSuccess(newsModel)
                } else {
                    callback.onFail()
                }
            } catch (e: Exception) {
                callback.onFail()
            }
        }
    }
}