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
import org.shortweather.util.TokenSharedPreference
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
            if (viewModel.timeWakeSuccess.value == true) { // Success가 true = 아이템이 선택되었음을 의미
                if (viewModel.timeReturnSuccess.value == true && viewModel.timeOutSuccess.value == true) { // 만약 다른 두 정보가 모두 입력되었다면
                    binding.btnInputTimeCheck.isEnabled = true // 버튼 활성화
                }
            } else { // 만약 하나라도 입력이 되어있지 않으면 버튼은 비활성화 상태 유지
                binding.btnInputTimeCheck.isEnabled = false
            }
        }
        viewModel.timeOutSuccess.observe(this) {
            if (viewModel.timeOutSuccess.value == true) {
                if (viewModel.timeReturnSuccess.value == true && viewModel.timeWakeSuccess.value == true) {
                    binding.btnInputTimeCheck.isEnabled = true
                }
            } else {
                binding.btnInputTimeCheck.isEnabled = false
            }
        }
        viewModel.timeReturnSuccess.observe(this) {
            if (viewModel.timeReturnSuccess.value == true) {
                if (viewModel.timeOutSuccess.value == true && viewModel.timeWakeSuccess.value == true) {
                    binding.btnInputTimeCheck.isEnabled = true
                }
            } else {
                binding.btnInputTimeCheck.isEnabled = false
            }
        }
    }

    private fun setOnClickListener() {
        // 확인 버튼을 누르면 MainActivity로 이동
        binding.btnInputTimeCheck.setOnClickListener() { // 메인 화면으로 이동
            // 서버에 7개의 데이터 전송 로직 필요
            val token = TokenSharedPreference.getToken(this)
            Log.d("token", "token:".plus(token))
            startActivity(Intent(this, MainActivity::class.java)) // 서버에 전달해주는 로직 추후에 필요
            finish()
        }

        binding.layoutTimeWake.setOnClickListener() { // 기상시간 선택
            BottomSheetTimeFragment.newInstance("wake").show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }

        binding.layoutTimeOut.setOnClickListener() { // 외출시간 선택
            BottomSheetTimeFragment.newInstance("out").show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }

        binding.layoutTimeReturn.setOnClickListener() { // 귀가시간 선택
            BottomSheetTimeFragment.newInstance("return").show(supportFragmentManager, BottomSheetTimeFragment.TAG)
        }
    }

    private fun saveInfo(){
        val gender = intent.getStringExtra("gender")
        val age = intent.getStringExtra("age")
        val sense = intent.getStringExtra("sense")
        viewModel.setBeforeInfo(gender!!, age!!, sense!!)
    }
}