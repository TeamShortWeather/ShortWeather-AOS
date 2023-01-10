package org.shortweather.presentation.input.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.shortweather.data.model.RequestUserInfo
import org.shortweather.domain.repository.AuthRepository
import org.shortweather.util.Event
import javax.inject.Inject

@HiltViewModel
class InputTimeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val deviceToken = MutableLiveData("") // 최초상태를 의미하는 하나의 공백 삽입
    fun setDeviceToken(Token: String) {
        deviceToken.value = Token
    }

    val timeWake = MutableLiveData(" ") // 최초상태를 의미하는 하나의 공백 삽입
    fun setTimeWake(waketime: String) {
        timeWake.value = waketime
    }

    val timeOut = MutableLiveData(" ")
    fun setTimeOut(outtime: String) {
        timeOut.value = outtime
    }

    val timeReturn = MutableLiveData(" ")
    fun setTimeReturn(returnstime: String) {
        timeReturn.value = returnstime
    }

    private val _gender = MutableLiveData(" ")
    private val _age = MutableLiveData(" ")
    private val _sense = MutableLiveData(" ")

    fun setBeforeInfo(gender: String, age: String, sense: String) {
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

    private val _createUserEvent = MutableLiveData<Event<Boolean>>()
    val createUserEvent: LiveData<Event<Boolean>>
        get() = _createUserEvent

    private val _searchUserEvent = MutableLiveData<Event<Boolean>>()
    val searchUserEvent: LiveData<Event<Boolean>>
        get() = _searchUserEvent

    fun checkAllTimeFiled(): Boolean { // 모든 시간이 정상적으로 입력되었다면 true
        return (timeReturnSuccess.value!! && timeOutSuccess.value!! && timeWakeSuccess.value!!)
    }

    fun createUser() {
        viewModelScope.launch {
            runCatching {
                authRepository.createUser(
                    RequestUserInfo(
                        _gender.value!!,
                        _age.value!!,
                        _sense.value!!,
                        timeWake.value!!,
                        timeOut.value!!,
                        timeReturn.value!!,
                        deviceToken.value!!
                    )
                )
            }.fold({
                _createUserEvent.value = Event(true)
            }, {
                _createUserEvent.value = Event(false)
            })
        }
    }

    fun searchUser() {
        viewModelScope.launch {
            runCatching {
                authRepository.searchUser(
                    deviceToken.value!!
                )
            }.fold({
                _searchUserEvent.value = Event(true)
            }, {
                _searchUserEvent.value = Event(false)
            })
        }
    }
}