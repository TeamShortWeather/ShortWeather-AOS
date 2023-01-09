package org.shortweather.data.model

data class RequestUserInfo(
    val gender: String,
    val age: String,
    val sense: String,
    val wakeTime: String,
    val outTime: String,
    val returnTime: String,
    val deviceToken: String
)
