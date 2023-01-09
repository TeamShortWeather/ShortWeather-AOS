package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputInfoBinding
import org.shortweather.presentation.input.viewmodel.InputInfoViewModel
import org.shortweather.util.binding.BindingActivity


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

    private fun setObservers() { // 뷰모델 관찰 돌입, 리팩토링 필요
        viewModel.isGenderSuccess.observe(this) {
            binding.btnInputInfoNext.isEnabled = viewModel.checkAllInputFiled()
        } // 이하 동문
        viewModel.isAgeSuccess.observe(this) {
            binding.btnInputInfoNext.isEnabled = viewModel.checkAllInputFiled()
        }
        viewModel.isSenseSuccess.observe(this) {
            binding.btnInputInfoNext.isEnabled = viewModel.checkAllInputFiled()
        }
    }

    private fun setOnClickListener() {
        binding.btnInputInfoNext.setOnClickListener() { // 다음 버튼 클릭 시 InputTimeActivity로 이동,
            // intent로 선택한 3개의 값을 다음 activity에 전달해주어야함
            val intent = Intent(this, InputTimeActivity::class.java)
            intent.putExtra("gender", viewModel.getGender())
            intent.putExtra("age", viewModel.getAge())
            intent.putExtra("sense", viewModel.getSense())
            startActivity(Intent(intent))
        }

        binding.layoutGender.setOnClickListener() { // 성별 선택란 클릭 시
            binding.vInputInfoGenderLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputInfoGender.setTextColor(R.color.short_weather_black)
            binding.btnGender.setBackgroundResource(R.drawable.ic_expand)
            BottomSheetFragment.newInstance("gender")
                .show(supportFragmentManager, BottomSheetFragment.TAG)
        }

        binding.layoutAge.setOnClickListener() { // 연령 선택란 클릭 시
            binding.vInputInfoAgeLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputInfoAge.setTextColor(R.color.short_weather_black)
            binding.btnAge.setBackgroundResource(R.drawable.ic_expand)
            BottomSheetFragment.newInstance("age")
                .show(supportFragmentManager, BottomSheetFragment.TAG)
        }

        binding.layoutSense.setOnClickListener() { // 민감도 선택란 클릭 시
            binding.vInputInfoSenseLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputInfoSense.setTextColor(R.color.short_weather_black)
            binding.btnSense.setBackgroundResource(R.drawable.ic_expand)
            BottomSheetFragment.newInstance("sense")
                .show(supportFragmentManager, BottomSheetFragment.TAG)
        }
    }

}