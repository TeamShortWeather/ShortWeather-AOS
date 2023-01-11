package org.shortweather.presentation.customweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.shortweather.data.model.CustomWeatherPrecipitation
import org.shortweather.data.model.CustomWeatherTemp
import org.shortweather.domain.repository.CustomWeatherRepository
import org.shortweather.util.Constants.WEATHER
import javax.inject.Inject

@HiltViewModel
class CustomWeatherViewModel @Inject constructor(
    private val customWeatherRepository: CustomWeatherRepository
) : ViewModel() {
    private val _customWeatherTempList = MutableLiveData<List<CustomWeatherTemp>>()
    val customWeatherTempList: LiveData<List<CustomWeatherTemp>>
        get() = _customWeatherTempList

    private val _customWeatherRainList = MutableLiveData<List<CustomWeatherPrecipitation>>()
    val customWeatherRainList: LiveData<List<CustomWeatherPrecipitation>>
        get() = _customWeatherRainList

    val isWeather = MutableLiveData(true)
    val isPrecipitation = MutableLiveData(false)

    fun setTimeZoneWeather(target: String) {
        if (target == WEATHER) {
            isWeather.value = true
            isPrecipitation.value = false
        } else {
            isWeather.value = false
            isPrecipitation.value = true
        }
    }

    fun getTemp() {
        viewModelScope.launch {
            runCatching {
                customWeatherRepository.getTemp()
            }.fold({
//                Log.d("tag", it.data.toString())
                val customWeatherTempList = mutableListOf<CustomWeatherTemp>()
                val data = it.data!!
                for (i in data.indices) {
                    if (i == 0) {
                        customWeatherTempList.add(CustomWeatherTemp(data[i], true))
                    } else {
                        customWeatherTempList.add(CustomWeatherTemp(data[i]))
                    }
                }
                _customWeatherTempList.value = customWeatherTempList
            }, {
                //error
            })
        }
    }

    fun getRain() {
        viewModelScope.launch {
            runCatching {
                customWeatherRepository.getRain()
            }.fold({
                val customWeatherRainList = mutableListOf<CustomWeatherPrecipitation>()
                val data = it.data!!
                for (i in data.indices) {
                    if (i == 0) {
                        customWeatherRainList.add(CustomWeatherPrecipitation(data[i], true))
                    } else {
                        customWeatherRainList.add(CustomWeatherPrecipitation(data[i]))
                    }
                }
                _customWeatherRainList.value = customWeatherRainList
            }, {
                //error
            })
        }
    }

}