package org.shortweather.presentation.todayweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.shortweather.data.model.*
import org.shortweather.domain.repository.TodayWeatherRepository
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

    fun setToastEvent(isSelect: Boolean) {
        this.isSelect.value = isSelect
    }

    fun getTodayWeatherInfo(accessToken: String) {
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
            }, {
                // error 처리 필요
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
            }, {
                // error 처리 필요
            })
        }
    }
}