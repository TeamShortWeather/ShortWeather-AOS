package org.shortweather.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T? = null
)