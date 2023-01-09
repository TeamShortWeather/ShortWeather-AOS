package org.shortweather.data.model

// 1/10 00:45 -> 기상시간, 외출/귀가시간 수정 기능 구현 잠정적 연기, 추후 활용 예정

data class RequestOtherTime(
    val goOutTime: String,
    val goHomeTime: String
)

data class ResponseOtherTime(
    val id: Int,
    val goOutTime: String,
    val goHomeTime: String
)

data class RequestWakeTime(
    val wakeUpTime: String
)

data class ResponseWakeTime(
    val id: Int,
    val wakeUpTime: String
)