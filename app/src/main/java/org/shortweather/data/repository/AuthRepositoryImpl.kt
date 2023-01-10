package org.shortweather.data.repository

import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.RequestUserInfo
import org.shortweather.data.model.ResponseUserCheck
import org.shortweather.data.model.ResponseUserInfo
import org.shortweather.data.source.remote.AuthRemoteDataSource
import org.shortweather.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authRemoteDataSource: AuthRemoteDataSource) :
    AuthRepository {
    override suspend fun searchUser(deviceToken: String): BaseResponse<ResponseUserCheck> =
        authRemoteDataSource.searchUser(deviceToken)

    override suspend fun createUser(requestUserInfo: RequestUserInfo): BaseResponse<ResponseUserInfo> =
        authRemoteDataSource.createUser(requestUserInfo)
}