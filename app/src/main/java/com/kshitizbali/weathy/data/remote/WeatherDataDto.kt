package com.kshitizbali.weathy.data.remote

import com.squareup.moshi.Json
data class WeatherDataDto(
    /*val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>,
    @field:Json(name = "relativehumidity_2m")
    val humidities: List<Double>*/

    @field:Json(name = "dt")
    val timeStamp: Long,
    @field:Json(name = "main")
    val main: MainDto,
    @field:Json(name = "weather")
    val weather: List<WeatherDto>,
    @field:Json(name = "wind")
    val wind: WindDto,
    @field:Json(name = "dt_txt")
    val dateTime : String

)
