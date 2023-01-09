package org.shortweather.presentation.todayweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.shortweather.data.model.FineDust
import org.shortweather.data.model.Newsflash
import org.shortweather.data.model.TodayWeatherTag
import org.shortweather.data.model.UltrafineDust
import javax.inject.Inject

@HiltViewModel
class TodayWeatherViewModel @Inject constructor() : ViewModel() {
    private val tags = listOf(Newsflash("폭염특보"), FineDust(""), UltrafineDust(""))
    val isSelect = MutableLiveData(false)

    fun getTags(): List<TodayWeatherTag> = tags

    fun setToastEvent(isSelect: Boolean) {
        this.isSelect.value = isSelect
    }
}