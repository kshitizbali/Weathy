package com.kshitizbali.weathy.domain.repository

import com.kshitizbali.weathy.domain.util.Resource
import com.kshitizbali.weathy.domain.weather.CurrentWeatherInfo
import com.kshitizbali.weathy.domain.weather.WeatherInfo

/**
 * WeatherRepository is the interface that provides the methods to fetch the weather data.
 */
interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>

    suspend fun getWeatherDataByCity(city: String): Resource<WeatherInfo>

    suspend fun getCurrentWeather(lat: Double, long: Double): Resource<CurrentWeatherInfo>

    suspend fun getCurrentWeatherByCity(city: String): Resource<CurrentWeatherInfo>
}