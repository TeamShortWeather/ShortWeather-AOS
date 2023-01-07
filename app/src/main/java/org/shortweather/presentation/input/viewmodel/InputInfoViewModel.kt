package org.shortweather.presentation.input.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputInfoViewModel @Inject constructor() : ViewModel() {

    var inputGender = MutableLiveData<String>(" ") // 최초상태를 의미하는 하나의 공백 삽입
    var inputAge = MutableLiveData<String>(" ")
    var inputSense = MutableLiveData<String>(" ")

    val isGenderSelected: LiveData<Boolean> = Transformations.map(inputGender) { it -> // 선택되지 않았다면 빈칸 -> false처리됨
        it.isNotEmpty()
    }
    val isGenderSuccess: LiveData<Boolean> = Transformations.map(inputGender) { it -> // 하나의 공백(최초상태)가 아니면서 빈칸도 아님 -> 내용 담겼음을 확인
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
}