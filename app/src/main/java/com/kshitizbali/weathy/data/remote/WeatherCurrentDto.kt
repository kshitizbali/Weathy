package com.kshitizbali.weathy.data.remote

import com.squareup.moshi.Json
data class WeatherCurrentDto(
    @field:Json(name = "weather")
    val weatherData: List<WeatherDto>,
    @field:Json(name = "main")
    val main: MainDto,
    @field:Json(name = "wind")
    val wind : WindDto,
    @field:Json(name = "dt")
    val dateTime: Long,
    @field:Json(name = "sys")
    val system : SysCurrentDto,
    @field:Json(name = "name")
    val name: String
)
