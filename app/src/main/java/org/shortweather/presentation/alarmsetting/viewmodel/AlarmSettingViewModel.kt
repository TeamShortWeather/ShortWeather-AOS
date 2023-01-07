package org.shortweather.presentation.alarmsetting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AlarmSettingViewModel @Inject constructor(): ViewModel() {
    val isChange = MutableLiveData(false)

    fun setIsChange(select: Boolean) {
        isChange.value = select
    }
}