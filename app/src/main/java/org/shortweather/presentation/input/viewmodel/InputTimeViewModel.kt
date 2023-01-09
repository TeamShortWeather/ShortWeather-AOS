package org.shortweather.presentation.input.viewmodel

import android.icu.text.IDNA.Info
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.shortweather.data.model.InfoDTO
import org.shortweather.util.Event
import javax.inject.Inject

@HiltViewModel
class InputTimeViewModel @Inject constructor() : ViewModel() {

    val timeWake = MutableLiveData(" ") // 최초상태를 의미하는 하나의 공백 삽입
    val timeOut = MutableLiveData(" ")
    val timeReturn = MutableLiveData(" ")
    val timeSettingWake = MutableLiveData(" ")
    val timeSettingOut = MutableLiveData(" ")
    val timeSettingReturn = MutableLiveData(" ")

    private val _gender = MutableLiveData(" ")
    val gender: LiveData<String>
        get() = _gender

    private val _age = MutableLiveData(" ")
    val age: LiveData<String>
        get() = _age

    private val _sense = MutableLiveData(" ")
    val sense: LiveData<String>
        get() = _sense

    fun setBeforeInfo(gender: String, age: String, sense: String){
        _gender.value = gender
        _age.value = age
        _sense.value = sense
    }

    private val _isWakeDestroy = MutableLiveData<Event<Boolean>>()
    val isWakeDestroy: LiveData<Event<Boolean>>
        get() = _isWakeDestroy

    private val _isOutDestroy = MutableLiveData<Event<Boolean>>()
    val isOutDestroy: LiveData<Event<Boolean>>
        get() = _isOutDestroy

    private val _isReturnDestroy = MutableLiveData<Event<Boolean>>()
    val isReturnDestroy: LiveData<Event<Boolean>>
        get() = _isReturnDestroy

    fun setIsWakeDestroy(select: Boolean) {
        _isWakeDestroy.value = Event(select)
    }

    fun setIsOutDestroy(select: Boolean) {
        _isOutDestroy.value = Event(select)
    }

    fun setIsReturnDestroy(select: Boolean) {
        _isReturnDestroy.value = Event(select)
    }

    val timeWakeSelected: LiveData<Boolean> =
        Transformations.map(timeWake) { it -> // 선택되지 않았다면 빈칸 -> false처리됨
            it.isNotEmpty()
        }
    val timeWakeSuccess: LiveData<Boolean> =
        Transformations.map(timeWake) { it -> // 하나의 공백(최초상태)가 아니면서 빈칸도 아님 -> 내용 담겼음을 확인
            !(it.equals(" ") || it.equals(""))
        }

    val timeOutSelected: LiveData<Boolean> =
        Transformations.map(timeOut) {
            it.isNotEmpty()
        }
    val timeOutSuccess: LiveData<Boolean> =
        Transformations.map(timeOut) {
            !(it.equals(" ") || it.equals(""))
        }

    val timeReturnSelected: LiveData<Boolean> =
        Transformations.map(timeReturn) {
            it.isNotEmpty()
        }
    val timeReturnSuccess: LiveData<Boolean> =
        Transformations.map(timeReturn) {
            !(it.equals(" ") || it.equals(""))
        }

}