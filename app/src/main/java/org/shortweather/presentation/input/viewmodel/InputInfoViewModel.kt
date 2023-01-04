package org.shortweather.presentation.input.viewmodel

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.shortweather.presentation.input.BottomSheetItem
import org.shortweather.presentation.input.bottomsheet.BottomSheetGender
import javax.inject.Inject

@HiltViewModel
class InputInfoViewModel: ViewModel() {

    val inputGender = MutableLiveData<String>().apply{ value = " " } // 최초에 하나의 공백 삽입
    val isGenderSelected: LiveData<Boolean> = Transformations.map(inputGender) { it ->
        validCheck(it)
    }

/*    val inputAgecheck : LiveData<Boolean> = Transformations.map(inputAge) { it ->
        validcheck(it)
    }

    val inputSensecheck : LiveData<Boolean> = Transformations.map(inputSense) { it ->
        validcheck(it)
    }*/

    private fun validCheck(input: String) : Boolean {
        if(input.isEmpty()){ // 아예 공백까지 없어야 false 반환
            return false
        }
        return true
    }
}