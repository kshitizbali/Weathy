package com.kshitizbali.weathy.data.remote

import com.kshitizbali.weathy.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    //Weather forecast by city
    @GET("forecast?&cnt=10&units=imperial&APPID="+BuildConfig.API_KEY)
    suspend fun getWeatherByCity(
        @Query("q") city: String
    ) : WeatherMainDto


    //Forecast by lat and lon
    @GET("forecast?&cnt=10&units=imperial&appid="+BuildConfig.API_KEY)
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ) : WeatherMainDto

    @GET("weather?&units=imperial&appid="+BuildConfig.API_KEY)
    suspend fun getCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ) : WeatherCurrentDto

    @GET("weather?&units=imperial&appid="+BuildConfig.API_KEY)
    suspend fun getCurrentWeatherByCity(
        @Query("q") city: String
    ) : WeatherCurrentDto
}