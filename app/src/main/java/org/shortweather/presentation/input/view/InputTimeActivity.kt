package org.shortweather.presentation.input.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputTimeBinding
import org.shortweather.presentation.MainActivity
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel
import org.shortweather.util.Event
import org.shortweather.util.EventObserver
import org.shortweather.util.ShortWeatherSharedPreference
import org.shortweather.util.binding.BindingActivity
import org.shortweather.util.extension.showToast
import org.shortweather.util.onThrottleClick

@AndroidEntryPoint
class InputTimeActivity : BindingActivity<ActivityInputTimeBinding>(R.layout.activity_input_time) {
    private val viewModel by viewModels<InputTimeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel // 데이터바인딩
        binding.lifecycleOwner = this
        binding.btnInputTimeCheck.isEnabled = false // 최초에는 확인 버튼의 Enable 상태 false
        saveBeforeInfo()
        setOnClickListener()
        setObservers()
    }

    private fun setObservers() { // 각 정보들의 입력을 관찰하며 모든 정보가 입력되었을 경우 버튼 활성화
        viewModel.timeWakeSuccess.observe(this) {
            binding.btnInputTimeCheck.isEnabled = viewModel.checkAllTimeFiled()
        }
        viewModel.timeOutSuccess.observe(this) {
            binding.btnInputTimeCheck.isEnabled = viewModel.checkAllTimeFiled()
        }
        viewModel.timeReturnSuccess.observe(this) {
            binding.btnInputTimeCheck.isEnabled = viewModel.checkAllTimeFiled()
        }
        viewModel.accessTokenEvent.observe(this, EventObserver { accessToken -> // 액세스토큰 저장
            if (accessToken != null) {
                ShortWeatherSharedPreference.setAccessToken(this, accessToken)
            }
        })
        viewModel.createUserEvent.observe(this, EventObserver { it -> // 액세스토큰 저장
            if(it){
                val mainIntent = Intent(this@InputTimeActivity, MainActivity::class.java)
                mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // 이전 activity 소멸
                startActivity(mainIntent)
            } else {
                showToast(getString(R.string.input_time_server_error), false)
            }
        })
    }

    private fun setOnClickListener() {
        binding.btnInputTimeCheck.onThrottleClick { // 확인 버튼 클릭
            // viewModel.setDeviceToken(ShortWeatherSharedPreference.getToken(this)) // 디바이스 토큰 설정
            viewModel.setDeviceToken("b")// 가상의 디바이스 토큰을 담은 테스트 코드
            viewModel.createUser() // 7개의 정보를 서버에 전송하고 메인 화면으로 이동 시도
        }

        binding.layoutTimeWake.onThrottleClick { // 기상시간 선택
            BottomSheetTimeFragment.newInstance("wake")
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
            binding.vInputTimeWakeLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputTimeWake.setTextColor(getColor(R.color.short_weather_gray_7))
            binding.btnWake.setBackgroundResource(R.drawable.ic_expand)
        }

        binding.layoutTimeOut.onThrottleClick { // 외출시간 선택
            BottomSheetTimeFragment.newInstance("out")
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
            binding.vInputTimeOutLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputTimeOut.setTextColor(getColor(R.color.short_weather_gray_7))
            binding.btnOut.setBackgroundResource(R.drawable.ic_expand)
        }

        binding.layoutTimeReturn.onThrottleClick { // 귀가시간 선택
            BottomSheetTimeFragment.newInstance("return")
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
            binding.vInputTimeReturnLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputTimeReturn.setTextColor(getColor(R.color.short_weather_gray_7))
            binding.btnReturn.setBackgroundResource(R.drawable.ic_expand)
        }
    }

    private fun saveBeforeInfo() { // 이전 activity에서 받은 3개의 정보를 저장
        val gender = intent.getStringExtra("gender")
        val age = intent.getStringExtra("age")
        val sense = intent.getStringExtra("sense")
        viewModel.setBeforeInfo(gender!!, age!!, sense!!)
    }
}