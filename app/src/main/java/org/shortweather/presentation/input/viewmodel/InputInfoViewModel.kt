package org.shortweather.presentation.input.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputInfoViewModel @Inject constructor() : ViewModel() {

    val inputGender = MutableLiveData(" ") // 최초상태를 의미하는 하나의 공백 삽입
    val inputAge = MutableLiveData(" ")
    val inputSense = MutableLiveData(" ")
    val isGenderSelected: LiveData<Boolean> = // 빈칸인지 아닌지 확인하여 아이템 선택이 취소되었는지 관찰하기 위함
        Transformations.map(inputGender) { it ->
            it.isNotEmpty()
        }
    val isAgeSelected: LiveData<Boolean> = Transformations.map(inputAge) { it ->
        it.isNotEmpty()
    }
    val isSenseSelected: LiveData<Boolean> = Transformations.map(inputSense) { it ->
        it.isNotEmpty()
    }
    val isGenderSuccess: LiveData<Boolean> = // "한칸 공백도 아니고 빈칸도 아님 -> 아이템 선택 성공" 여부를 관찰하기 위함
        Transformations.map(inputGender) { it ->
            !(it.equals(" ") || it.equals(""))
        }
    val isAgeSuccess: LiveData<Boolean> = Transformations.map(inputAge) { it ->
        !(it.equals(" ") || it.equals(""))
    }
    val isSenseSuccess: LiveData<Boolean> = Transformations.map(inputSense) { it ->
        !(it.equals(" ") || it.equals(""))
    }

    fun getGender(): String { // getter
        return inputGender.value!!
    }

    fun setGender(gender: String) { // setter
        inputGender.value = gender
    }

    fun getAge(): String {
        return inputAge.value!!
    }

    fun setAge(age: String) {
        inputAge.value = age
    }

    fun getSense(): String {
        return inputSense.value!!
    }

    fun setSense(sense: String) {
        inputSense.value = sense
    }

    fun checkAllInputFiled(): Boolean { // 모든 정보가 입력되면 다음 버튼을 활성화시키기 위한 함수
        return (isGenderSuccess.value!! && isAgeSuccess.value!! && isSenseSuccess.value!!)
    }
}