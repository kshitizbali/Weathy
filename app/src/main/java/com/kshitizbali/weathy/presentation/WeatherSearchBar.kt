package com.kshitizbali.weathy.presentation

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kshitizbali.weathy.R

data class City(val city: String, val state: String)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WeatherSearchBar(onCityClick: (String) -> Unit) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val cities = remember {
        parseJsonFromRawFile(context = context)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText = remember { mutableStateOf("") }

    val filteredCities = cities.filter {
        it.city.contains(
            searchText.value,
            ignoreCase = true
        ) && searchText.value.length >= 2
    }

    Surface(
        modifier = Modifier
            .padding(16.dp,16.dp,16.dp,8.dp)
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        Column {
            TextField(
                value = searchText.value,
                onValueChange = { searchText.value = it },
                label = { Text("Search City") },
                modifier = Modifier.fillMaxWidth()
            )
            if (searchText.value.isNotEmpty()) {
                LazyColumn {
                    items(filteredCities.size) { city ->
                        val citySuggestion = filteredCities[city]
                        CitySuggestion(
                            city = citySuggestion,
                            onCityClick = onCityClick,
                            searchText,
                            keyboardController = keyboardController,
                            focusManager = focusManager
                        )
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CitySuggestion(
    city: City,
    onCityClick: (String) -> Unit,
    searchQueryState: MutableState<String>,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
) {
    Text(
        text = "${city.city}, ${city.state}",
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onCityClick(city.city)
                keyboardController?.hide()
                searchQueryState.value = ""
                focusManager.clearFocus()
            }
    )
}

@Preview
@Composable
fun PreviewSearchBar() {
    WeatherSearchBar(onCityClick = {})
}


private fun parseJsonFromRawFile(context: Context): List<City> {
    val jsonData = context.resources.openRawResource(R.raw.uscities).bufferedReader()
        .use { it.readText() }
    val gson = Gson()
    val listType = object : TypeToken<List<City>>() {}.type
    return gson.fromJson(jsonData, listType)
}