package com.kshitizbali.weathy.presentation

import com.kshitizbali.weathy.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
