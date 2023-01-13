package org.shortweather.presentation.alarmsetting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmSettingViewModel @Inject constructor(): ViewModel() {
    val isChange = MutableLiveData(true)

    fun setIsChange(select: Boolean) {
        isChange.value = select
    }
}