package org.shortweather.presentation.input.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputTimeViewModel @Inject constructor() : ViewModel() {

    var timeWake = MutableLiveData<String>(" ") // 최초상태를 의미하는 하나의 공백 삽입
    var timeOut = MutableLiveData<String>(" ")
    var timeReturn = MutableLiveData<String>(" ")

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