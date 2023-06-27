package com.kshitizbali.weathy.domain.weather

import java.time.LocalDateTime

data class CurrentWeatherData(
    val time: LocalDateTime,
    val temperatureFahren: Double,
    val pressure: Int,
    val windSpeed: Double,
    val humidity: Int,
    val weatherType: WeatherType,
    val name: String
)
