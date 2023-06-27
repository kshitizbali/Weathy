package com.kshitizbali.weathy.data.remote

import com.squareup.moshi.Json
data class MainDto(
    @field:Json(name = "temp")
    val temperature: Double,
    @field:Json(name = "feels_like")
    val feelsLike: Double,
    @field:Json(name = "temp_min")
    val tempMin: Double,
    @field:Json(name = "temp_max")
    val tempMax: Double,
    @field:Json(name = "pressure")
    val pressure: Int,
    @field:Json(name = "humidity")
    val humidity: Int,
)