package org.shortweather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _isSetting = MutableLiveData(false)
    val isSetting: LiveData<Boolean>
        get() = _isSetting

    fun setTitle(text: String) {
        _title.value = text
    }

    fun setIsSetting(setting: Boolean) {
        _isSetting.value = setting
    }
}