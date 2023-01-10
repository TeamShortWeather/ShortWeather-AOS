package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputTimeBinding
import org.shortweather.presentation.MainActivity
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel
import org.shortweather.util.ShortWeatherSharedPreference
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class InputTimeActivity : BindingActivity<ActivityInputTimeBinding>(R.layout.activity_input_time) {
    private val viewModel by viewModels<InputTimeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel // 데이터바인딩
        binding.lifecycleOwner = this
        binding.btnInputTimeCheck.isEnabled = false // 최초에는 확인 버튼의 Enable 상태 false
        saveInfo()
        setOnClickListener()
        setObservers()
    }

    private fun setObservers() {
        viewModel.timeWakeSuccess.observe(this) {
            binding.btnInputTimeCheck.isEnabled = viewModel.checkAllTimeFiled()
        }
        viewModel.timeOutSuccess.observe(this) {
            binding.btnInputTimeCheck.isEnabled = viewModel.checkAllTimeFiled()
        }
        viewModel.timeReturnSuccess.observe(this) {
            binding.btnInputTimeCheck.isEnabled = viewModel.checkAllTimeFiled()
        }
    }

    private fun setOnClickListener() {
        binding.btnInputTimeCheck.setOnClickListener() { // 메인 화면으로 이동
            viewModel.setDeviceToken(ShortWeatherSharedPreference.getToken(this)) // 디바이스 토큰 설정
            viewModel.createUser() // 서버통신 개시
            startActivity(Intent(this, MainActivity::class.java)) // 서버에 전달해주는 로직 추후에 필요
            finish()
        }

        binding.layoutTimeWake.setOnClickListener() { // 기상시간 선택
            BottomSheetTimeFragment.newInstance("wake")
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }

        binding.layoutTimeOut.setOnClickListener() { // 외출시간 선택
            BottomSheetTimeFragment.newInstance("out")
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }

        binding.layoutTimeReturn.setOnClickListener() { // 귀가시간 선택
            BottomSheetTimeFragment.newInstance("return")
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }
    }

    private fun saveInfo() {
        val gender = intent.getStringExtra("gender")
        val age = intent.getStringExtra("age")
        val sense = intent.getStringExtra("sense")
        viewModel.setBeforeInfo(gender!!, age!!, sense!!)
    }
}