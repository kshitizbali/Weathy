package com.kshitizbali.weathy.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kshitizbali.weathy.domain.location.LocationTracker
import com.kshitizbali.weathy.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kshitizbali.weathy.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * ViewModel for the Weather screen.
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

     var currentState by mutableStateOf(CurrentWeatherState())
        internal set

     var state by mutableStateOf(WeatherState())
        internal set


    fun loadWeatherInfo() {
        viewModelScope.launch {
            currentState = currentState.copy(
                isLoading = true,
                error = null
            )
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result =
                    repository.getCurrentWeather(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        currentState = currentState.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                        when (val resultForecast =
                            repository.getWeatherData(location.latitude, location.longitude)) {
                            is Resource.Success -> {
                                state = state.copy(
                                    weatherInfo = resultForecast.data,
                                    isLoading = false,
                                    error = null
                                )
                            }

                            is Resource.Error -> {
                                state = state.copy(
                                    weatherInfo = null,
                                    isLoading = false,
                                    error = resultForecast.message
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        currentState = currentState.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                currentState = currentState.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }

    fun loadWeatherInfoByCity(city: String) {

        viewModelScope.launch {
            currentState = currentState.copy(
                isLoading = true,
                error = null
            )
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result =
                    repository.getCurrentWeatherByCity(city = city)) {
                    is Resource.Success -> {
                        currentState = currentState.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                        when (val resultForecast =
                            repository.getWeatherDataByCity(city = city)) {
                            is Resource.Success -> {
                                state = state.copy(
                                    weatherInfo = resultForecast.data,
                                    isLoading = false,
                                    error = null
                                )
                            }

                            is Resource.Error -> {
                                state = state.copy(
                                    weatherInfo = null,
                                    isLoading = false,
                                    error = resultForecast.message
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        currentState = currentState.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                currentState = currentState.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }

    fun checkInternetAvailability(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities =
            connectivityManager.activeNetwork ?: return false

        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }

    fun checkInternetAvailabilityAsync(context: Context, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isConnected = withContext(Dispatchers.IO) {
                checkInternetAvailability(context)
            }
            onResult(isConnected)
        }
    }
}