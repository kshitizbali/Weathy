package com.kshitizbali.weathy.data.repository

import android.util.Log
import com.kshitizbali.weathy.data.mappers.toCurrentWeatherInfo
import com.kshitizbali.weathy.data.mappers.toWeatherInfo
import com.kshitizbali.weathy.data.remote.WeatherApi
import com.kshitizbali.weathy.domain.repository.WeatherRepository
import com.kshitizbali.weathy.domain.util.Resource
import com.kshitizbali.weathy.domain.weather.CurrentWeatherInfo
import com.kshitizbali.weathy.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Log.d(
                "Bali getWeatherData", "" + api.getWeatherData(
                    lat = lat,
                    lon = long
                )
            )
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    lon = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getWeatherDataByCity(city: String): Resource<WeatherInfo> {
        return try {
            Log.d(
                "Bali city", "" + api.getWeatherByCity(
                    city.plus(",us")
                )
            )
            Log.d(
                "Bali city", "" + api.getWeatherByCity(
                    city.plus(",us")
                ).toWeatherInfo()
            )

            Resource.Success(
                data = api.getWeatherByCity(
                    city.plus(",us")
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getCurrentWeather(
        lat: Double,
        long: Double
    ): Resource<CurrentWeatherInfo> {
        return try {
            Log.d(
                "Bali getCurrentWeat", "" + api.getCurrentWeatherData(
                    lat = lat,
                    lon = long
                )
            )
            Resource.Success(
                data = api.getCurrentWeatherData(
                    lat = lat,
                    lon = long
                ).toCurrentWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getCurrentWeatherByCity(city: String): Resource<CurrentWeatherInfo> {
        return try {
            Resource.Success(
                data = api.getCurrentWeatherByCity(
                    city.plus(",us")
                ).toCurrentWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}