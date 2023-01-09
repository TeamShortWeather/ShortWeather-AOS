package org.shortweather.presentation.setting.view

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityWakeTimeSettingBinding
import org.shortweather.presentation.input.view.BottomSheetTimeFragment
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel
import org.shortweather.util.EventObserver
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class WakeTimeSettingActivity :
    BindingActivity<ActivityWakeTimeSettingBinding>(R.layout.activity_wake_time_setting) {
    private val viewModel by viewModels<InputTimeViewModel>()

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
            if (isSuccess) {
                binding.vSettingWakeTimeLine.setBackgroundResource(R.color.short_weather_gray_1)
                binding.btnSettingWakeTimeCheck.isEnabled = true
            }
        })
    }

    private fun setOnClickListener() {
        // 확인 버튼을 누르면 MainActivity로 이동
        binding.btnSettingWakeTimeCheck.setOnClickListener() { // 메인 액티비티의 설정 프래그먼트로 이동
            // 서버에 시간 전달
            finish()
        }

        binding.layoutSettingWakeTime.setOnClickListener() { // 기상시간 선택
            binding.vSettingWakeTimeLine.setBackgroundResource(R.color.short_weather_blue)
            BottomSheetTimeFragment.newInstance("wake").show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }
    }
}