package org.shortweather.presentation.input.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.shortweather.util.Event
import javax.inject.Inject

@HiltViewModel
class InputTimeViewModel @Inject constructor() : ViewModel() {

    val timeWake = MutableLiveData(" ") // 최초상태를 의미하는 하나의 공백 삽입
    fun setTimeWake(wake: String){
        timeWake.value = wake
    }
    val timeOut = MutableLiveData(" ")
    fun setTimeOut(out: String){
        timeOut.value = out
    }
    val timeReturn = MutableLiveData(" ")
    fun setTimeReturn(returns: String){
        timeReturn.value = returns
    }

    private val _gender = MutableLiveData(" ")
    private val _age = MutableLiveData(" ")
    private val _sense = MutableLiveData(" ")

    fun setBeforeInfo(gender: String, age: String, sense: String){
        _gender.value = gender
        _age.value = age
        _sense.value = sense
    }

    private val _isWakeDestroy = MutableLiveData<Event<Boolean>>()
    val isWakeDestroy: LiveData<Event<Boolean>>
        get() = _isWakeDestroy
    fun setIsWakeDestroy(select: Boolean) {
        _isWakeDestroy.value = Event(select)
    }

    private val _isOutDestroy = MutableLiveData<Event<Boolean>>()
    val isOutDestroy: LiveData<Event<Boolean>>
        get() = _isOutDestroy
    fun setIsOutDestroy(select: Boolean) {
        _isOutDestroy.value = Event(select)
    }


    private val _isReturnDestroy = MutableLiveData<Event<Boolean>>()
    val isReturnDestroy: LiveData<Event<Boolean>>
        get() = _isReturnDestroy
    fun setIsReturnDestroy(select: Boolean) {
        _isReturnDestroy.value = Event(select)
    }

    val timeWakeSelected: LiveData<Boolean> =
        Transformations.map(timeWake) { it -> // 선택되지 않았다면 빈칸 -> false처리
            it.isNotEmpty()
        }
    val timeWakeSuccess: LiveData<Boolean> =
        Transformations.map(timeWake) { it -> // 하나의 공백(최초상태)가 아니면서 빈칸도 아님 -> 바텀시트 아이템이 입력됨
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

    fun checkAllTimeFiled(): Boolean{ // 모든 시간이 정상적으로 입력되었다면 true
        return (timeReturnSuccess.value!! && timeOutSuccess.value!! && timeWakeSuccess.value!!)
    }

}