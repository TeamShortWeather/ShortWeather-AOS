package org.shortweather.presentation.othertimesetting.view

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityOtherTimeSettingBinding
import org.shortweather.presentation.input.view.BottomSheetTimeFragment
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel
import org.shortweather.util.Constants.OUT
import org.shortweather.util.Constants.RETURN
import org.shortweather.util.EventObserver
import org.shortweather.util.binding.BindingActivity
import org.shortweather.util.extension.setOnThrottleClickListener

@AndroidEntryPoint
class OtherTimeSettingActivity :
    BindingActivity<ActivityOtherTimeSettingBinding>(R.layout.activity_other_time_setting) {
    private val viewModel by viewModels<InputTimeViewModel>()
    private var beforeOut = " "
    private var beforeReturn = " "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel // 데이터바인딩
        binding.lifecycleOwner = this
        binding.btnSettingOtherTimeCheck.isEnabled = false // 최초에는 확인 버튼의 Enable 상태 false
        setOnClickListener()
        setObservers()
    }

    private fun setObservers() {
        viewModel.isOutDestroy.observe(this, EventObserver { isSuccess ->
            if (isSuccess && checkTimeChanged(OUT)) {
                binding.vSettingOutTimeLine.setBackgroundResource(R.color.short_weather_blue)
                binding.btnSettingOtherTimeCheck.isEnabled = true
            } else {
                binding.vSettingOutTimeLine.setBackgroundResource(R.color.short_weather_gray_1)
                binding.btnSettingOtherTimeCheck.isEnabled = false
            }
        })
        viewModel.isReturnDestroy.observe(this, EventObserver { isSuccess ->
            if (isSuccess && checkTimeChanged(RETURN)) {
                binding.vSettingReturnTimeLine.setBackgroundResource(R.color.short_weather_blue)
                binding.btnSettingOtherTimeCheck.isEnabled = true
            } else { // 바텀시트 취소
                binding.vSettingReturnTimeLine.setBackgroundResource(R.color.short_weather_gray_1)
                binding.btnSettingOtherTimeCheck.isEnabled = false
            }
        })
    }

    private fun setOnClickListener() {
        // 확인 버튼을 누르면 MainActivity로 이동
        binding.btnSettingOtherTimeCheck.setOnThrottleClickListener {
            // 메인 액티비티의 설정 프래그먼트로 이동
            // 서버에 시간 전달
            finish()
        }
        binding.layoutSettingOutTime.setOnThrottleClickListener() { // 외출시간 선택
            beforeOut = viewModel.timeOut.value!! // 호출 전 외출시간 저장
            binding.vSettingOutTimeLine.setBackgroundResource(R.color.short_weather_blue)
            BottomSheetTimeFragment.newInstance("out")
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }
        binding.layoutSettingReturnTime.setOnThrottleClickListener() { // 귀가시간 선택
            beforeReturn = viewModel.timeReturn.value!! // 호출 전 귀가시간 저장
            binding.vSettingReturnTimeLine.setBackgroundResource(R.color.short_weather_blue)
            BottomSheetTimeFragment.newInstance("return")
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }
    }

    private fun checkTimeChanged(target: String): Boolean { // 호출 전후 설정된 외출시간 또는 귀가시간이 달라지면 true
        if (target == OUT) {
            if (viewModel.timeOut.value == beforeOut) {
                return false
            }
        } else {
            if (viewModel.timeReturn.value == beforeReturn) {
                return false
            }
        }
        return true
    }
}