package org.shortweather.presentation.customweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.shortweather.data.model.CustomWeatherPrecipitation
import org.shortweather.data.model.CustomWeatherWeather
import org.shortweather.util.Constants.WEATHER
import javax.inject.Inject

@HiltViewModel
class CustomWeatherViewModel @Inject constructor() : ViewModel() {
    val isWeather = MutableLiveData(true)
    val isPrecipitation = MutableLiveData(false)
    val weatherList = listOf(
        CustomWeatherWeather("1", "22"),
        CustomWeatherWeather("2", "22"),
        CustomWeatherWeather("3", "22"),
        CustomWeatherWeather("4", "22"),
        CustomWeatherWeather("5", "22"),
        CustomWeatherWeather("6", "22")
    )
    val precipitationList = listOf(
        CustomWeatherPrecipitation("1", "23"),
        CustomWeatherPrecipitation("2", "23"),
        CustomWeatherPrecipitation("3", "23"),
        CustomWeatherPrecipitation("4", "23"),
        CustomWeatherPrecipitation("5", "23"),
        CustomWeatherPrecipitation("6", "23"),
    )

    fun setTimeZoneWeather(target: String) {
        if (target == WEATHER) {
            isWeather.value = true
            isPrecipitation.value = false
        } else {
            isWeather.value = false
            isPrecipitation.value = true
        }
    }
}