package org.shortweather.presentation.sample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.shortweather.data.model.Sample
import org.shortweather.domain.repository.SampleRepository
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleRepository: SampleRepository
): ViewModel() {
    private val _sample = MutableLiveData<Sample>()
    val sample: LiveData<Sample>
        get() = _sample

    fun getSample() {
        viewModelScope.launch {
            runCatching {
                sampleRepository.getSample()
            }.onSuccess {
                _sample.value = it
            }.onFailure {
                // 예외 처리를 작성합니다.
            }
        }
    }
}