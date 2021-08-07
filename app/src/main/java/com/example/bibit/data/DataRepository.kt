package com.example.bibit.data

import com.example.bibit.data.local.LocalRepository
import com.example.bibit.data.remote.RemoteRepository
import com.example.bibit.data.remote.ServiceResponse
import javax.inject.Inject

class DataRepository @Inject
constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : DataSource {

    override suspend fun requestNews(): ServiceResponse? {
        return remoteRepository.requestNews()
    }

}
