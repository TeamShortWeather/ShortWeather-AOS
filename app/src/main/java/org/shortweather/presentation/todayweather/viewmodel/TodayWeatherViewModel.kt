package org.shortweather.presentation.todayweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.shortweather.data.model.*
import org.shortweather.domain.repository.TodayWeatherRepository
import org.shortweather.util.Constants.FAILURE
import org.shortweather.util.Constants.HTTP_EXCEPTION_400
import org.shortweather.util.Constants.HTTP_EXCEPTION_401
import org.shortweather.util.Constants.HTTP_EXCEPTION_500
import org.shortweather.util.Constants.SUCCESS_200
import org.shortweather.util.Event
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class TodayWeatherViewModel @Inject constructor(
    private val todayWeatherRepository: TodayWeatherRepository
) : ViewModel() {
    private val _tags = MutableLiveData<List<TodayWeatherTag>>()
    val tags: LiveData<List<TodayWeatherTag>>
        get() = _tags
    val isSelect = MutableLiveData(false)
    private val _todayWeatherInfo = MutableLiveData<ResponseTodayWeatherInfo>()
    val todayWeatherInfo: LiveData<ResponseTodayWeatherInfo>
        get() = _todayWeatherInfo
    private val _todayWeatherToastInfo = MutableLiveData<ResponseTodayWeatherToastInfo>()
    val todayWeatherToastInfo: LiveData<ResponseTodayWeatherToastInfo>
        get() = _todayWeatherToastInfo
    private val _refreshEvent = MutableLiveData<Event<Boolean>>()
    val refreshEvent: LiveData<Event<Boolean>>
        get() = _refreshEvent
    private val _todayWeatherInfoEvent = MutableLiveData<Event<Int>>()
    val todayWeatherInfoEvent: LiveData<Event<Int>>
        get() = _todayWeatherInfoEvent
    private val _todayWeatherToastInfoEvent = MutableLiveData<Event<Int>>()
    val todayWeatherToastInfoEvent: LiveData<Event<Int>>
        get() = _todayWeatherToastInfoEvent

    fun setToastEvent(isSelect: Boolean) {
        this.isSelect.value = isSelect
    }

    fun getTodayWeatherInfo(accessToken: String, isRefresh: Boolean = false) {
        viewModelScope.launch {
            runCatching {
                todayWeatherRepository.getTodayWeatherInfo(accessToken)
            }.fold({
                val todayWeatherInfo = it.data!!
                _todayWeatherInfo.value = todayWeatherInfo
                val tags = mutableListOf<TodayWeatherTag>()
                if (todayWeatherInfo.breakingNews != null) {
                    tags.add(Newsflash(todayWeatherInfo.breakingNews))
                }
                tags.add(FineDust(todayWeatherInfo.fineDust))
                tags.add(UltrafineDust(todayWeatherInfo.ultrafineDust))
                _tags.value = tags
                if (isRefresh) {
                    _refreshEvent.value = Event(true)
                }
                _todayWeatherInfoEvent.value = Event(SUCCESS_200)
            }, {
                if (it is HttpException) {
                    when (it.code()) {
                        400 -> _todayWeatherInfoEvent.value = Event(HTTP_EXCEPTION_400)
                        401 -> _todayWeatherInfoEvent.value = Event(HTTP_EXCEPTION_401)
                        500 -> _todayWeatherInfoEvent.value = Event(HTTP_EXCEPTION_500)
                        else -> throw IllegalArgumentException("not found error.")
                    }
                } else {
                    _todayWeatherInfoEvent.value = Event(FAILURE)
                }
            })
        }
    }

    fun getTodayWeatherToastInfo(accessToken: String) {
        viewModelScope.launch {
            runCatching {
                todayWeatherRepository.getTodayWeatherToastInfo(accessToken)
            }.fold({
                _todayWeatherToastInfo.value = it.data!!
                isSelect.value = true
                _todayWeatherToastInfoEvent.value = Event(SUCCESS_200)
            }, {
                if (it is HttpException) {
                    when (it.code()) {
                        400 -> _todayWeatherToastInfoEvent.value = Event(HTTP_EXCEPTION_400)
                        401 -> _todayWeatherToastInfoEvent.value = Event(HTTP_EXCEPTION_401)
                        500 -> _todayWeatherToastInfoEvent.value = Event(HTTP_EXCEPTION_500)
                        else -> throw IllegalArgumentException("not found error.")
                    }
                } else {
                    _todayWeatherToastInfoEvent.value = Event(FAILURE)
                }
            })
        }
    }
}