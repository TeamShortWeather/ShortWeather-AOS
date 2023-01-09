package org.shortweather.data.model

data class BaseResponse<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T? = null
)

// 유저 등록 조회 Request -> Header로 바로 처리

// 유저 등록 조회 Response
data class ResponseUserCheck(
    val deviceToken: String,
    val accessToken: String,
    val isExist: Boolean
)

// 유저 정보 입력 Request
data class RequestUserInfo(
    val gender: String,
    val age: String,
    val tempSens: String,
    val wakeUpTime: String,
    val goOutTime: String,
    val goHomeTime: String,
    val deviceToken: String,
)

// 유저 정보 입력 Response
data class ResponseUserInfo(
    val id: Int,
    val gender: String,
    val age: String,
    val tempSens: String,
    val wakeUpTime: String,
    val goOutTime: String,
    val goHomeTime: String,
    val deviceToken: String,
    val accessToken: String
)
