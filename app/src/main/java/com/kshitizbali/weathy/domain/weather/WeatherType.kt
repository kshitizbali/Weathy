package com.kshitizbali.weathy.domain.weather

import androidx.annotation.DrawableRes
import com.kshitizbali.weathy.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    object ThunderStorm : WeatherType(
        weatherDesc = "ThunderStorm",
        iconRes = R.drawable.ic_rainythunder
    )

    object Drizzle : WeatherType(
        weatherDesc = "Drizzle",
        iconRes = R.drawable.ic_rainshower
    )

    object Rain : WeatherType(
        weatherDesc = "Rain",
        iconRes = R.drawable.ic_rainy
    )

    object FreezingRain : WeatherType(
        weatherDesc = "Freezing Rain",
        iconRes = R.drawable.ic_snowyrainy
    )

    object Snow : WeatherType(
        weatherDesc = "Snow",
        iconRes = R.drawable.ic_heavysnow
    )

    object Atmosphere : WeatherType(
        weatherDesc = "Atmosphere",
        iconRes = R.drawable.ic_pressure
    )

    object ClearSky : WeatherType(
        weatherDesc = "Clear day",
        iconRes = R.drawable.ic_sunny
    )

    object FewClouds : WeatherType(
        weatherDesc = "Few clouds.",
        iconRes = R.drawable.ic_sunnycloudy
    )

    object ScatteredClouds : WeatherType(
        weatherDesc = "Scattered clouds.",
        iconRes = R.drawable.ic_very_cloudy
    )

    object BrokenClouds : WeatherType(
        weatherDesc = "Broken clouds night",
        iconRes = R.drawable.ic_very_cloudy
    )

    object OvercastClouds : WeatherType(
        weatherDesc = "Overcast clouds night",
        iconRes = R.drawable.ic_very_cloudy
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                200 -> ThunderStorm
                201 -> ThunderStorm
                202 -> ThunderStorm
                210 -> ThunderStorm
                211 -> ThunderStorm
                212 -> ThunderStorm
                221 -> ThunderStorm
                230 -> ThunderStorm
                231 -> ThunderStorm
                232 -> ThunderStorm
                300 -> Drizzle
                301 -> Drizzle
                302 -> Drizzle

                310 -> Drizzle

                311 -> Drizzle

                312 -> Drizzle

                313 -> Drizzle

                314 -> Drizzle

                321 -> Drizzle

                500 -> Rain

                501 -> Rain

                502 -> Rain

                503 -> Rain

                504 -> Rain

                511 -> FreezingRain

                520 -> Rain

                521 -> Rain

                522 -> Rain

                531 -> Rain

                600 -> Snow

                601 -> Snow

                602 -> Snow

                611 -> Snow

                612 -> Snow

                613 -> Snow

                615 -> Snow

                616 -> Snow

                620 -> Snow

                621 -> Snow

                622 -> Snow

                701 -> Atmosphere

                711 -> Atmosphere

                721 -> Atmosphere

                731 -> Atmosphere

                741 -> Atmosphere

                751 -> Atmosphere

                761 -> Atmosphere

                762 -> Atmosphere

                771 -> Atmosphere

                781 -> Atmosphere

                800 -> ClearSky
                801 -> FewClouds
                802 -> ScatteredClouds
                803 -> BrokenClouds
                804 -> OvercastClouds

                else -> ClearSky
            }
        }
    }
}