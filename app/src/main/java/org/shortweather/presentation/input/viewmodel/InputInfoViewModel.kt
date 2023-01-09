package org.shortweather.presentation.input.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputInfoViewModel @Inject constructor() : ViewModel() {

    val _inputGender = MutableLiveData<String>(" ") // 최초상태를 의미하는 하나의 공백 삽입
    val inputGender: LiveData<String>
        get() = _inputGender
    fun getGender(): String{
        return _inputGender.value!!
    }
    fun setGender(gender: String){
        _inputGender.value = gender
    }

    val _inputAge = MutableLiveData<String>(" ")
    val inputAge: LiveData<String>
        get() = _inputAge
    fun getAge(): String{
        return _inputGender.value!!
    }
    fun setAge(age: String){
        _inputAge.value = age
    }

    val _inputSense = MutableLiveData<String>(" ")
    val inputSense: LiveData<String>
        get() = _inputSense
    fun getSense(): String{
        return _inputGender.value!!
    }
    fun setSense(sense: String){
        _inputSense.value = sense
    }


    val isGenderSelected: LiveData<Boolean> = Transformations.map(_inputGender) { it -> // 선택되지 않았다면 빈칸 -> false처리됨
        it.isNotEmpty()
    }
    val isGenderSuccess: LiveData<Boolean> = Transformations.map(_inputGender) { it -> // 하나의 공백(최초상태)가 아니면서 빈칸도 아님 -> 내용 담겼음을 확인
        !(it.equals(" ") || it.equals(""))
    }

    val isAgeSelected: LiveData<Boolean> = Transformations.map(_inputAge) { it ->
        it.isNotEmpty()
    }
    val isAgeSuccess: LiveData<Boolean> = Transformations.map(_inputAge) { it ->
        !(it.equals(" ") || it.equals(""))
    }

    val isSenseSelected: LiveData<Boolean> = Transformations.map(_inputSense) { it ->
        it.isNotEmpty()
    }
    val isSenseSuccess: LiveData<Boolean> = Transformations.map(_inputSense) { it ->
        !(it.equals(" ") || it.equals(""))
    }

    fun checkAllInputFiled(): Boolean{ // 모든 정보가 입력되면 다음 버튼을 활성화시키기 위한 함수
        return (isGenderSuccess.value!! && isAgeSuccess.value!! && isSenseSuccess.value!!)
    }
}