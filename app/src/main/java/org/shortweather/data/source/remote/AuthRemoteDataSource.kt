package org.shortweather.data.source.remote

import org.shortweather.data.api.AuthService
import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.RequestUserInfo
import org.shortweather.data.model.ResponseUserCheck
import org.shortweather.data.model.ResponseUserInfo
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authservice: AuthService
) {
    suspend fun createUser(requestUserInfo: RequestUserInfo): BaseResponse<ResponseUserInfo> =
        authservice.createUser(requestUserInfo)

    suspend fun searchUser(deviceToken: String): BaseResponse<ResponseUserCheck> =
        authservice.searchUser(deviceToken)
}
