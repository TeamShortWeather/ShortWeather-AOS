package org.shortweather.presentation.input.bottomsheet

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TimePicker
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.BottomSheetTimeContentBinding
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel


@AndroidEntryPoint
class BottomSheetTime(val target: String) : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetTimeContentBinding
    val viewModel by activityViewModels<InputTimeViewModel>() // 이제 이 뷰모델은 activity의 뷰모델 객체를 공유하는 개념, 별개의 객체가 아니다.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetTimeContentBinding.inflate(inflater, container, false)
        setTimeInterval(binding.tpInputTime)
        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        inputCancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewKindCheck()
    }

    private fun viewKindCheck() { // 바텀시트의 종류가 성별/연령/민감도인지 확인하고 이에 대응하여 아이템들을 생성함,
        if (target == "wake") {
            binding.tvBottomSheetTimeHeader.text = getString(R.string.setting_wake_up)
        } else if (target == "out") {
            binding.tvBottomSheetTimeHeader.text = getString(R.string.setting_out)
        } else {
            binding.tvBottomSheetTimeHeader.text = getString(R.string.setting_return)
        }
    }

    private fun inputCancel() { // 입력 실패 상황 (바텀시트가 등장한 상태에서 아무것도 선택하지 않고 바텀시트 이탈 시)
        if (target == "wake") {
            viewModel.timeWake.value = "" // 아무 값도 선택하지 않은 상태이므로 빈칸 처리 -> 뷰모델에서 false 처리됨
        } else if (target == "out") {
            viewModel.timeOut.value = ""
        } else {
            viewModel.timeReturn.value = ""
        }
    }

    companion object {
        const val TAG = "BottomSheetTime"
    }

    @SuppressLint("PrivateApi")
    private fun setTimeInterval(timePicker: TimePicker) {
        val timeInterval = if(target == "wake") 30 else 60 // 기상시간은 30분 간격, 나머지는 60분 간격
        val displayedValues: ArrayList<String> = ArrayList()
        val minutePicker: NumberPicker = timePicker.findViewById(
            Resources.getSystem().getIdentifier(
                "minute", "id", "android"
            )
        )

        minutePicker.minValue = 0
        minutePicker.maxValue = (60 / timeInterval) - 1
        minutePicker.setOnValueChangedListener(null) // 분이 바뀌어도 시에 영향을 주지 않음.
        for (i in 0 until 60 step timeInterval) { // 시간 간격 반영된 분 삽입
            displayedValues.add(String.format("%02d", i))
        }
        minutePicker.displayedValues = displayedValues.toTypedArray()
    }

}