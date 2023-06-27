package com.kshitizbali.weathy.data.mappers

import com.kshitizbali.weathy.data.remote.WeatherCurrentDto
import com.kshitizbali.weathy.data.remote.WeatherDataDto
import com.kshitizbali.weathy.data.remote.WeatherMainDto
import com.kshitizbali.weathy.domain.weather.CurrentWeatherData
import com.kshitizbali.weathy.domain.weather.CurrentWeatherInfo
import com.kshitizbali.weathy.domain.weather.WeatherData
import com.kshitizbali.weathy.domain.weather.WeatherInfo
import com.kshitizbali.weathy.domain.weather.WeatherType
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun WeatherCurrentDto.toCurrentWeatherInfo(): CurrentWeatherInfo {
    return CurrentWeatherInfo(
        CurrentWeatherData(
            time = Instant.ofEpochSecond(dateTime)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime(),
            temperatureFahren = main.temperature,
            pressure = main.pressure,
            windSpeed = wind.speed,
            humidity = main.humidity,
            weatherType = WeatherType.fromWMO(weatherData.first().id),
            name = name
        )
    )
}

fun WeatherMainDto.toWeatherInfo(): WeatherInfo {
    val today = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val filteredForecastByToday =  weatherData.filter { it.dateTime.startsWith(today) }
        .map {
            convertToWeatherData(0,it)
        }
    val weatherDataMap = mapOf(0 to filteredForecastByToday)

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap
    )
}

private fun convertToWeatherData(index: Int, data: WeatherDataDto): WeatherData {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val temperature = data.main.temperature
    val weatherCode = data.weather[0].id
    val windSpeed = data.wind.speed
    val pressure = data.main.pressure
    val humidity = data.main.humidity
    val dateTime = data.dateTime
    return WeatherData(
        time = LocalDateTime.parse(
            dateTime,
            dateFormatter
        ),
        temperatureFahren = temperature,
        pressure = pressure,
        windSpeed = windSpeed,
        humidity = humidity,
        weatherType = WeatherType.fromWMO(weatherCode)
    )
}

private fun getCurrentDate(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return currentDate.format(formatter)
}

private fun extractDateFromDateTime(dateTime: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(dateTime, formatter).toLocalDate().toString()
}
