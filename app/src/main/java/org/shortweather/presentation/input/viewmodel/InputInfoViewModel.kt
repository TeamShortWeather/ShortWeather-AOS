package org.shortweather.presentation.input.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputInfoViewModel @Inject constructor() : ViewModel() {

    val inputGender = MutableLiveData<String>(" ") // 최초상태를 의미하는 하나의 공백 삽입
    fun getGender(): String {
        return inputGender.value!!
    }

    fun setGender(gender: String) {
        inputGender.value = gender
    }

    val inputAge = MutableLiveData<String>(" ")
    fun getAge(): String {
        return inputGender.value!!
    }

    fun setAge(age: String) {
        inputAge.value = age
    }

    val inputSense = MutableLiveData<String>(" ")
    fun getSense(): String {
        return inputGender.value!!
    }

    fun setSense(sense: String) {
        inputSense.value = sense
    }


    val isGenderSelected: LiveData<Boolean> =
        Transformations.map(inputGender) { it -> // 선택되지 않았다면 빈칸 -> false처리됨
            it.isNotEmpty()
        }
    val isGenderSuccess: LiveData<Boolean> =
        Transformations.map(inputGender) { it -> // 하나의 공백(최초상태)가 아니면서 빈칸도 아님 -> 내용 담겼음을 확인
            !(it.equals(" ") || it.equals(""))
        }

    val isAgeSelected: LiveData<Boolean> = Transformations.map(inputAge) { it ->
        it.isNotEmpty()
    }
    val isAgeSuccess: LiveData<Boolean> = Transformations.map(inputAge) { it ->
        !(it.equals(" ") || it.equals(""))
    }

    val isSenseSelected: LiveData<Boolean> = Transformations.map(inputSense) { it ->
        it.isNotEmpty()
    }
    val isSenseSuccess: LiveData<Boolean> = Transformations.map(inputSense) { it ->
        !(it.equals(" ") || it.equals(""))
    }

    fun checkAllInputFiled(): Boolean { // 모든 정보가 입력되면 다음 버튼을 활성화시키기 위한 함수
        return (isGenderSuccess.value!! && isAgeSuccess.value!! && isSenseSuccess.value!!)
    }
}