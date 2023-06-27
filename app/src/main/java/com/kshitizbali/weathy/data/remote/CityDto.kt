package com.kshitizbali.weathy.data.remote

import com.squareup.moshi.Json


data class CityDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "sunrise")
    val sunrise: Long,
    @field:Json(name = "sunset")
    val sunset: Long
)

