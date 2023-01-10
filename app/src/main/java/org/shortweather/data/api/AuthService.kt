package org.shortweather.data.api

import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.RequestUserInfo
import org.shortweather.data.model.ResponseUserCheck
import org.shortweather.data.model.ResponseUserInfo
import retrofit2.http.*

interface AuthService {
    @POST("auth") // 유저 정보 입력
    suspend fun createUser(
        @Body requestUserInfo: RequestUserInfo
    ): BaseResponse<ResponseUserInfo>

    @GET("auth/login")// 유저 등록 조회
    suspend fun searchUser(
        @Header("deviceToken") deviceToken: String
    ): BaseResponse<ResponseUserCheck>
}