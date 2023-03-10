package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputInfoBinding
import org.shortweather.presentation.input.viewmodel.InputInfoViewModel
import org.shortweather.util.Constants.AGE
import org.shortweather.util.Constants.GENDER
import org.shortweather.util.Constants.SENSE
import org.shortweather.util.binding.BindingActivity
import org.shortweather.util.extension.setOnThrottleClickListener

@AndroidEntryPoint
class InputInfoActivity : BindingActivity<ActivityInputInfoBinding>(R.layout.activity_input_info) {
    private val viewModel by viewModels<InputInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel // 데이터바인딩
        binding.lifecycleOwner = this
        binding.btnInputInfoNext.isEnabled = false // 최초에는 다음 버튼의 Enable 상태 false
        setOnClickListener()
        setObservers()
    }

    private fun setObservers() { // 각 정보들의 입력을 관찰하며 모든 정보가 입력되었을 경우 버튼 활성화
        viewModel.isGenderSuccess.observe(this) {
            binding.btnInputInfoNext.isEnabled = viewModel.checkAllInputFiled()
        }
        viewModel.isAgeSuccess.observe(this) {
            binding.btnInputInfoNext.isEnabled = viewModel.checkAllInputFiled()
        }
        viewModel.isSenseSuccess.observe(this) {
            binding.btnInputInfoNext.isEnabled = viewModel.checkAllInputFiled()
        }
    }

    private fun setOnClickListener() {
        binding.btnInputInfoNext.setOnThrottleClickListener { // 다음 버튼 클릭 시 InputTimeActivity로 이동,
            // intent를 통해 입력한 3개의 정보를 다음 activity에 전달
            val intent = Intent(this, InputTimeActivity::class.java)
            intent.putExtra(GENDER, viewModel.getGender())
            intent.putExtra(AGE, viewModel.getAge())
            intent.putExtra(SENSE, viewModel.getSense())
            startActivity(Intent(intent))
        }

        binding.layoutGender.setOnThrottleClickListener { // 성별 선택란 클릭 시
            BottomSheetFragment.newInstance(GENDER)
                .show(supportFragmentManager, BottomSheetFragment.TAG)
            binding.vInputInfoGenderLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputInfoGender.setTextColor(getColor(R.color.short_weather_gray_7))
            binding.btnGender.setBackgroundResource(R.drawable.ic_expand)
        }

        binding.layoutAge.setOnThrottleClickListener { // 연령 선택란 클릭 시
            BottomSheetFragment.newInstance(AGE)
                .show(supportFragmentManager, BottomSheetFragment.TAG)
            binding.vInputInfoAgeLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputInfoAge.setTextColor(getColor(R.color.short_weather_gray_7))
            binding.btnAge.setBackgroundResource(R.drawable.ic_expand)
        }

        binding.layoutSense.setOnThrottleClickListener { // 민감도 선택란 클릭 시
            BottomSheetFragment.newInstance(SENSE)
                .show(supportFragmentManager, BottomSheetFragment.TAG)
            binding.vInputInfoSenseLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputInfoSense.setTextColor(getColor(R.color.short_weather_gray_7))
            binding.btnSense.setBackgroundResource(R.drawable.ic_expand)
        }
    }
}