package org.shortweather.data.model

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