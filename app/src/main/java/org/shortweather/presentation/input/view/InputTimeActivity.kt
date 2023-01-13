package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputTimeBinding
import org.shortweather.presentation.MainActivity
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel
import org.shortweather.util.Constants.AGE
import org.shortweather.util.Constants.GENDER
import org.shortweather.util.EventObserver
import org.shortweather.util.Constants.WAKE
import org.shortweather.util.Constants.OUT
import org.shortweather.util.Constants.RETURN
import org.shortweather.util.Constants.SENSE
import org.shortweather.util.ShortWeatherSharedPreference
import org.shortweather.util.binding.BindingActivity
import org.shortweather.util.extension.setOnThrottleClickListener
import org.shortweather.util.extension.showToast

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
        viewModel.createUserEvent.observe(this, EventObserver { it -> // 유저 등록 성공여부 관찰
            if (it) { // 유저 등록 성공 시 MainActivity로 이동
                val mainIntent = Intent(this, MainActivity::class.java)
                mainIntent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // 이전 activity 소멸
                startActivity(mainIntent)
            } else { // 서버에 유저 등록 실패 시 "잠시만 기다려주세요.. "토스트 메시지 출력
                showToast(getString(R.string.wait_server_error), false)
            }
        })
        viewModel.serverConnectEvent.observe(
            this,
            EventObserver { it -> // http 연결 자체가 실패하면 "인터넷에 연결해 주세요.." 토스트 메시지 출력
                if (!it) {
                    showToast(getString(R.string.http_server_error), false)
                }
            })
    }

    private fun setOnClickListener() {
        binding.btnInputTimeCheck.setOnThrottleClickListener { // 확인 버튼 클릭
            viewModel.setDeviceToken(ShortWeatherSharedPreference.getDeviceToken(this)) // 디바이스 토큰 설정
            viewModel.createUser() // 7개의 정보를 서버에 전송하고 메인 화면으로 이동 시도
        }

        binding.layoutTimeWake.setOnThrottleClickListener { // 기상시간 선택
            BottomSheetTimeFragment.newInstance(WAKE)
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
            binding.vInputTimeWakeLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputTimeWake.setTextColor(getColor(R.color.short_weather_gray_7))
            binding.btnWake.setBackgroundResource(R.drawable.ic_expand)
        }

        binding.layoutTimeOut.setOnThrottleClickListener { // 외출시간 선택
            BottomSheetTimeFragment.newInstance(OUT)
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
            binding.vInputTimeOutLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputTimeOut.setTextColor(getColor(R.color.short_weather_gray_7))
            binding.btnOut.setBackgroundResource(R.drawable.ic_expand)
        }

        binding.layoutTimeReturn.setOnThrottleClickListener { // 귀가시간 선택
            BottomSheetTimeFragment.newInstance(RETURN)
                .show(supportFragmentManager, BottomSheetTimeFragment.TAG)
            binding.vInputTimeReturnLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputTimeReturn.setTextColor(getColor(R.color.short_weather_gray_7))
            binding.btnReturn.setBackgroundResource(R.drawable.ic_expand)
        }
    }

    private fun saveBeforeInfo() { // 이전 activity에서 받은 3개의 정보를 저장
        val gender = intent.getStringExtra(GENDER)
        val age = intent.getStringExtra(AGE)
        val sense = intent.getStringExtra(SENSE)
        viewModel.setBeforeInfo(gender!!, age!!, sense!!)
    }
}