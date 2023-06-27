package com.kshitizbali.weathy.presentation

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.ui.unit.sp
import com.kshitizbali.weathy.data.local.getLastLocation
import com.kshitizbali.weathy.data.local.saveLastLocation


import com.kshitizbali.weathy.presentation.ui.theme.DarkBlue
import com.kshitizbali.weathy.presentation.ui.theme.WeathyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (viewModel.checkInternetAvailability(context = this)) {
                if (!getLastLocation(this).isNullOrEmpty()) {
                    viewModel.loadWeatherInfoByCity(city = getLastLocation(this)!!)
                } else {
                    viewModel.loadWeatherInfo()
                }
            } else {
                viewModel.currentState = viewModel.currentState.copy(
                    isLoading = false,
                    error = "No internet connection available. Please check your internet connection ."
                )
                viewModel.state = viewModel.state.copy(
                    isLoading = false,
                    error = "No internet connection available. Please check your internet connection ."
                )
            }
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
        setContent {
            WeathyTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                    ) {
                        WeatherSearchBar(onCityClick = ::getWeatherDataByCityRequest)
                        WeatherCard(
                            state = viewModel.currentState,
                            backgroundColor = Color.White.copy(alpha = 0.5f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = viewModel.state)
                    }
                    if (viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }

    private fun getWeatherDataByCityRequest(cityName: String) {
        viewModel.loadWeatherInfoByCity(city = cityName)
        saveLastLocation(context = this, value = cityName)
    }

    @Composable
    fun CheckInternetConnectivity() {
        val isConnected = remember { mutableStateOf(false) }
        val context = LocalContext.current

        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return

        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return

        isConnected.value = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

        // Use the value of isConnected for further processing or UI rendering
    }
}