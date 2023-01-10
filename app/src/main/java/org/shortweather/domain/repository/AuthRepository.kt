package org.shortweather.domain.repository

import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.RequestUserInfo
import org.shortweather.data.model.ResponseUserCheck
import org.shortweather.data.model.ResponseUserInfo

interface AuthRepository {
    suspend fun createUser(requestUserInfo: RequestUserInfo): BaseResponse<ResponseUserInfo>
    suspend fun searchUser(deviceToken: String): BaseResponse<ResponseUserCheck>
}