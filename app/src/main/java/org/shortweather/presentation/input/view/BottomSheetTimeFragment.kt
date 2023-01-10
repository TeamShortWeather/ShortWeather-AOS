package org.shortweather.presentation.input.view

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
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
import java.util.*

@AndroidEntryPoint
class BottomSheetTimeFragment : BottomSheetDialogFragment() {
    private var _binding: BottomSheetTimeContentBinding? = null
    private val binding: BottomSheetTimeContentBinding
        get() = requireNotNull(_binding) { "${this::class.java.simpleName} error." }
    private val viewModel by activityViewModels<InputTimeViewModel>() // 이제 이 뷰모델은 activity의 뷰모델 객체를 공유함. 별개의 객체가 아니다.
    private var target: String? = null
    private var timeInterval = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        target = requireArguments().getString("target") ?: ""
        _binding = BottomSheetTimeContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) { // 어떤 바텀시트에서 onCancel이 발생하는지 target을 통해 결정
        super.onCancel(dialog)
        when (target) {
            "wake" -> {
                viewModel.setIsWakeDestroy(true)
            }
            "out" -> {
                viewModel.setIsOutDestroy(true)
            }
            else -> {
                viewModel.setIsReturnDestroy(true)
            }
        }
        inputCancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTimeInterval(binding.tpInputTime)
        setOnClickListeners()
        viewKindCheck()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewKindCheck() { // 바텀시트의 종류가 성별/연령/민감도인지 확인하고 이에 대응하여 아이템들을 생성함,
        when (target) {
            "wake" -> {
                binding.tvBottomSheetTimeHeader.text = getString(R.string.setting_wake_up)
            }
            "out" -> {
                binding.tvBottomSheetTimeHeader.text = getString(R.string.setting_out)
            }
            else -> {
                binding.tvBottomSheetTimeHeader.text = getString(R.string.setting_return)
            }
        }
    }

    private fun inputCancel() { // 입력 실패 상황 (바텀시트가 등장한 상태에서 아무것도 선택하지 않고 바텀시트 이탈 시)
        when (target) {
            "wake" -> {
                viewModel.setTimeWake("") // 시간 초기화
            }
            "out" -> {
                viewModel.setTimeOut("")
            }
            else -> {
                viewModel.setTimeReturn("")
            }
        }
    }

    @SuppressLint("PrivateApi")
    private fun setTimeInterval(timePicker: TimePicker) {
        timeInterval = if (target == "wake") 30 else 60  // 기상시간은 30분 간격, 나머지는 60분 간격
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

    private fun setOnClickListeners() {
        binding.btnBottonSheetTime.setOnClickListener { // 시간 저장 버튼 클릭 시
            val minute = binding.tpInputTime.minute * timeInterval
            when (target) {
                "wake" -> {
                    viewModel.setTimeWakeReal(makeRealTime(binding.tpInputTime.hour, minute))
                    viewModel.setTimeWake(makeTime(binding.tpInputTime.hour, minute.toString()))
                    viewModel.setIsWakeDestroy(true)
                }
                "out" -> {
                    viewModel.setTimeOutReal(makeRealTime(binding.tpInputTime.hour, minute))
                    viewModel.setTimeOut(makeTime(binding.tpInputTime.hour, minute.toString()))
                    viewModel.setIsOutDestroy(true)
                }
                else -> {
                    viewModel.setTimeReturnReal(makeRealTime(binding.tpInputTime.hour, minute))
                    viewModel.setTimeReturn(makeTime(binding.tpInputTime.hour, minute.toString()))
                    viewModel.setIsReturnDestroy(true)
                }
            }
            dismiss() // 시간이 결정되었으므로 바텀시트 소멸
        }
    }

    private fun makeTime(hour: Int, minute: String): String {
        // "오전 OO시 OO분" 형식의 시간 반환
        var realminute = minute
        if (minute.toInt() < 10) {
            realminute = "0".plus(minute)
        }
        return if (hour > 12 || hour == 0) {
            if (hour == 0) {
                "오전 12시 ${realminute}분"
            } else {
                "오후 ${hour - 12}시 ${realminute}분"
            }
        } else {
            if (hour == 12) {
                "오후 12시 ${realminute}분"
            } else {
                "오전 ${hour}시 ${realminute}분"
            }
        }
    }

    private fun makeRealTime(hour: Int, minute: Int): String {
        // "0900" 형식의 시간 반환
        val formattedHour = if (hour < 10) "0".plus(hour.toString()) else hour.toString()
        val formattedMinute = if (minute == 0) "00" else minute.toString()
        return formattedHour.plus(formattedMinute)
    }

    companion object {
        const val TAG = "BottomSheetTime"

        @JvmStatic
        fun newInstance(target: String) = BottomSheetTimeFragment().apply {
            arguments = Bundle().apply {
                putString("target", target)
            }
        }
    }
}