package com.kshitizbali.weathy.presentation

import com.kshitizbali.weathy.domain.weather.CurrentWeatherInfo

data class CurrentWeatherState(
    val weatherInfo: CurrentWeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
