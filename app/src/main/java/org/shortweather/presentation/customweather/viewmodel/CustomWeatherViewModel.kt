package org.shortweather.presentation.customweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.shortweather.data.model.CustomWeatherPrecipitation
import org.shortweather.data.model.CustomWeatherTemp
import org.shortweather.data.model.ResponseCustomWeatherDetail
import org.shortweather.domain.repository.CustomWeatherRepository
import org.shortweather.util.Constants.FAILURE
import org.shortweather.util.Constants.SUCCESS_200
import org.shortweather.util.Constants.WEATHER
import org.shortweather.util.Event
import retrofit2.HttpException
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
    private val _customWeatherDetail = MutableLiveData<ResponseCustomWeatherDetail>()
    val customWeatherDetail: LiveData<ResponseCustomWeatherDetail>
        get() = _customWeatherDetail
    private val _customWeatherTempListEvent = MutableLiveData<Event<Int>>()
    val customWeatherTempListEvent: LiveData<Event<Int>>
        get() = _customWeatherTempListEvent
    private val _customWeatherRainListEvent = MutableLiveData<Event<Int>>()
    val customWeatherRainListEvent: LiveData<Event<Int>>
        get() = _customWeatherRainListEvent
    private val _customWeatherDetailEvent = MutableLiveData<Event<Int>>()
    val customWeatherDetailEvent: LiveData<Event<Int>>
        get() = _customWeatherDetailEvent
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
                _customWeatherTempListEvent.value = Event(SUCCESS_200)
            }, {
                if (it is HttpException) {
                    _customWeatherTempListEvent.value = Event(it.code())
                } else {
                    _customWeatherTempListEvent.value = Event(FAILURE)
                }
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
                _customWeatherRainListEvent.value = Event(SUCCESS_200)
            }, {
                if (it is HttpException) {
                    _customWeatherRainListEvent.value = Event(it.code())
                } else {
                    _customWeatherRainListEvent.value = Event(FAILURE)
                }
            })
        }
    }

    fun getDetail(accessToken: String) {
        viewModelScope.launch {
            runCatching {
                customWeatherRepository.getDetail(accessToken)
            }.fold({
                _customWeatherDetail.value = it.data!!
                _customWeatherDetailEvent.value = Event(SUCCESS_200)
            }, {
                if (it is HttpException) {
                    _customWeatherDetailEvent.value = Event(it.code())
                } else {
                    _customWeatherDetailEvent.value = Event(FAILURE)
                }
            })
        }
    }
}