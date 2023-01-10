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

    private val deviceToken = MutableLiveData("") // 최초상태를 의미하는 하나의 공백 삽입
    fun setDeviceToken(Token: String) {
        deviceToken.value = Token
    }

    private val timeWakeReal = MutableLiveData("") // "0900" 형식으로 시간 저장
    fun setTimeWakeReal(time: String) {
        timeWakeReal.value = time
    }

    val timeWake = MutableLiveData(" ") // "오전 OO시 OO분" 형식으로 시간 저장
    fun setTimeWake(waketime: String) {
        timeWake.value = waketime
    }

    private val timeOutReal = MutableLiveData("")
    fun setTimeOutReal(time: String) {
        timeOutReal.value = time
    }

    val timeOut = MutableLiveData(" ")
    fun setTimeOut(outtime: String) {
        timeOut.value = outtime
    }

    private val timeReturnReal = MutableLiveData("")
    fun setTimeReturnReal(time: String) {
        timeReturnReal.value = time
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
                        timeWakeReal.value!!,
                        timeOutReal.value!!,
                        timeReturnReal.value!!,
                        deviceToken.value!!
                    )
                )
            }.fold({
                _createUserEvent.value = Event(true)
                _accessTokenEvent.value = Event(it.data?.accessToken)
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
                _accessTokenEvent.value = Event(it.data?.accessToken)
            }, {
                _searchUserEvent.value = Event(false)
            })
        }
    }

    private val _accessTokenEvent = MutableLiveData<Event<String?>>()
    val accessTokenEvent: LiveData<Event<String?>>
        get() = _accessTokenEvent

}