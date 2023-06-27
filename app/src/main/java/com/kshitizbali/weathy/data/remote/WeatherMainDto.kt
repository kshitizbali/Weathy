package com.kshitizbali.weathy.data.remote

import com.squareup.moshi.Json
data class WeatherMainDto(
    /*@field:Json(name = "hourly")
    val weatherData: WeatherDataDto*/
    @field:Json(name = "list")
    val weatherData: List<WeatherDataDto>,
    @field:Json(name = "city")
    val city: CityDto
)

