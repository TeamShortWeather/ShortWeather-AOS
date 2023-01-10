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
    private val timeWakeReal = MutableLiveData("") // "0900" 형식으로 시간 저장
    private val timeOutReal = MutableLiveData("")
    private val timeReturnReal = MutableLiveData("")
    val timeWake = MutableLiveData(" ") // "오전 OO시 OO분" 형식으로 시간 저장
    val timeOut = MutableLiveData(" ")
    val timeReturn = MutableLiveData(" ")
    private val gender = MutableLiveData(" ") // 이전 activity에서 얻은 3개의 정보를 서버 전송을 위해 저장
    private val age = MutableLiveData(" ")
    private val sense = MutableLiveData(" ")

    private val _isWakeDestroy = MutableLiveData<Event<Boolean>>() // 특정 바텀시트가 종료되었는지 여부를 저장
    val isWakeDestroy: LiveData<Event<Boolean>>
        get() = _isWakeDestroy
    private val _isOutDestroy = MutableLiveData<Event<Boolean>>()
    val isOutDestroy: LiveData<Event<Boolean>>
        get() = _isOutDestroy
    private val _isReturnDestroy = MutableLiveData<Event<Boolean>>()
    val isReturnDestroy: LiveData<Event<Boolean>>
        get() = _isReturnDestroy
    private val _createUserEvent = MutableLiveData<Event<Boolean>>() // 유저 추가 성공 여부를 일회성으로 관찰함
    val createUserEvent: LiveData<Event<Boolean>>
        get() = _createUserEvent
    private val _searchUserEvent = MutableLiveData<Event<Boolean>>() // 유저 조회 성공 여부를 일회성으로 관찰함
    val searchUserEvent: LiveData<Event<Boolean>>
        get() = _searchUserEvent
    private val _accessTokenEvent = MutableLiveData<Event<String?>>() // 액세스 토큰 추출 성공 여부를 일회성으로 관찰함
    val accessTokenEvent: LiveData<Event<String?>>
        get() = _accessTokenEvent

    val timeWakeSelected: LiveData<Boolean> =
        Transformations.map(timeWake) { it -> // 빈칸인지 아닌지 확인하여 시간 선택이 취소되었는지 관찰하기 위함
            it.isNotEmpty()
        }
    val timeWakeSuccess: LiveData<Boolean> =
        Transformations.map(timeWake) { it -> // "한칸 공백도 아니고 빈칸도 아님 -> 시간 선택 성공" 여부를 관찰하기 위함
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

    fun setDeviceToken(Token: String) { // 디바이스 토큰 설정
        deviceToken.value = Token
    }

    fun setTimeWakeReal(time: String) {
        timeWakeReal.value = time
    }

    fun setTimeWake(waketime: String) {
        timeWake.value = waketime
    }

    fun setTimeOutReal(time: String) {
        timeOutReal.value = time
    }

    fun setTimeOut(outtime: String) {
        timeOut.value = outtime
    }

    fun setTimeReturnReal(time: String) {
        timeReturnReal.value = time
    }

    fun setTimeReturn(returnstime: String) {
        timeReturn.value = returnstime
    }

    fun setBeforeInfo(inputgender: String, inputage: String, inputsense: String) { // 첫번째 정보입력폼의 정보 저장
        gender.value = inputgender
        age.value = inputage
        sense.value = inputsense
    }

    fun setIsWakeDestroy(select: Boolean) {
        _isWakeDestroy.value = Event(select)
    }

    fun setIsOutDestroy(select: Boolean) {
        _isOutDestroy.value = Event(select)
    }

    fun setIsReturnDestroy(select: Boolean) {
        _isReturnDestroy.value = Event(select)
    }

    fun checkAllTimeFiled(): Boolean { // 모든 시간이 정상적으로 입력되었다면 true
        return (timeReturnSuccess.value!! && timeOutSuccess.value!! && timeWakeSuccess.value!!)
    }

    fun createUser() { // 정보입력폼에서 얻은 6개의 정보와 디바이스 토큰을 서버에 전송
        viewModelScope.launch {
            runCatching {
                authRepository.createUser(
                    RequestUserInfo(
                        gender.value!!,
                        age.value!!,
                        sense.value!!,
                        timeWakeReal.value!!,
                        timeOutReal.value!!,
                        timeReturnReal.value!!,
                        deviceToken.value!!
                    )
                )
            }.fold({ // 전송 성공 시
                _createUserEvent.value = Event(true)
                _accessTokenEvent.value = Event(it.data?.accessToken)
            }, { // 전송 실패 시
                _createUserEvent.value = Event(false)
            })
        }
    }

    fun searchUser() { // 디바이스 토큰의 서버 존재 여부를 확인하기 위해 디바이스 토큰을 서버에 전송
        viewModelScope.launch {
            runCatching {
                authRepository.searchUser(
                    deviceToken.value!!
                )
            }.fold({ // 전송 성공 시
                _searchUserEvent.value = Event(true)
                _accessTokenEvent.value = Event(it.data?.accessToken)
            }, { // 전송 실패 시
                _searchUserEvent.value = Event(false)
            })
        }
    }

}