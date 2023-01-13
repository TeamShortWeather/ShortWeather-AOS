package org.shortweather.presentation.waketimesetting.view

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityWakeTimeSettingBinding
import org.shortweather.presentation.input.view.BottomSheetTimeFragment
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel
import org.shortweather.util.EventObserver
import org.shortweather.util.binding.BindingActivity
import org.shortweather.util.extension.setOnThrottleClickListener

@AndroidEntryPoint
class WakeTimeSettingActivity :
    BindingActivity<ActivityWakeTimeSettingBinding>(R.layout.activity_wake_time_setting) {
    private val viewModel by viewModels<InputTimeViewModel>()
    private var beforeTime = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel // 데이터바인딩
        binding.lifecycleOwner = this

        binding.btnSettingWakeTimeCheck.isEnabled = false // 최초에는 확인 버튼의 Enable 상태 false
        setOnClickListener()
        setObservers()
    }

    private fun setObservers() {
        viewModel.isWakeDestroy.observe(this, EventObserver { isSuccess ->
            if (isSuccess && checkTimeChanged()) {
                binding.vSettingWakeTimeLine.setBackgroundResource(R.color.short_weather_blue)
                binding.btnSettingWakeTimeCheck.isEnabled = true
            } else { // 기상시간 변경 없이 단순히 선택되었다가 취소된 상황
                binding.vSettingWakeTimeLine.setBackgroundResource(R.color.short_weather_gray_1)
                binding.btnSettingWakeTimeCheck.isEnabled = false
            }
        })
    }

    private fun setOnClickListener() {
        // 확인 버튼을 누르면 MainActivity로 이동
        binding.btnSettingWakeTimeCheck.setOnThrottleClickListener {
            // 메인 액티비티의 설정 프래그먼트로 이동
            // 서버에 시간 전달
            finish()
        }
        binding.layoutSettingWakeTime.setOnThrottleClickListener { // 기상시간 선택 바텀시트 호출
            beforeTime = viewModel.timeWake.value!! // 호출 전 기상시간 저장
            binding.vSettingWakeTimeLine.setBackgroundResource(R.color.short_weather_blue)
            BottomSheetTimeFragment.newInstance("wake")
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }
    }

    private fun checkTimeChanged(): Boolean { // 호출 전후 설정된 기상시간이 달라지면 true
        if (viewModel.timeWake.value == beforeTime) {
            return false
        }
        return true
    }
}