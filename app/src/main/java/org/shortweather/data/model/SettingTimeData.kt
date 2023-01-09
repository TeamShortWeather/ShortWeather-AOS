package org.shortweather.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestOtherTime(
    val goOutTime: String,
    val goHomeTime: String
)

@Serializable
data class ResponseOtherTime(
    val id: Int,
    val goOutTime: String,
    val goHomeTime: String
)

@Serializable
data class RequestWakeTime(
    val wakeUpTime: String
)

@Serializable
data class ResponseWakeTime(
    val id: Int,
    val wakeUpTime: String
)
