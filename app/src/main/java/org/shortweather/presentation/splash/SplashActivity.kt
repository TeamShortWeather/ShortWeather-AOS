package org.shortweather.presentation.splash

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import org.shortweather.databinding.ActivitySplashBinding
import org.shortweather.presentation.MainActivity
import org.shortweather.presentation.input.view.InputInfoActivity
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel
import org.shortweather.util.EventObserver
import org.shortweather.util.ShortWeatherSharedPreference
import org.shortweather.util.binding.BindingActivity
import org.shortweather.util.extension.showToast

@AndroidEntryPoint
class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val viewModel by viewModels<InputTimeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setDeviceToken("sk") // 테스트를 위한 가상의 디바이스 토큰 설정
        // viewModel.setDeviceToken(ShortWeatherSharedPreference.getToken(this)) // 디바이스 토큰 설정
        setObservers()
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.searchUser() // 디바이스 토큰을 서버에 전송하여 유저 조회
        }, SPLASH_TIME)
    }

    private fun setObservers() {
        viewModel.searchUserEvent.observe( // 유저 조회 성공 시 Main으로, 실패 시 정보입력으로
            this, EventObserver { code ->
                when (code) {
                    200 -> {
                        if (viewModel.getIsExist()) {
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            startActivity(Intent(this, InputInfoActivity::class.java))
                        }
                        finish()
                    }
                    400 -> showToast(getString(R.string.wait_server_error), false)
                    500 -> showToast(getString(R.string.wait_server_error), false)
                    0 -> showToast(getString(R.string.http_server_error), false)
                }
            })
        viewModel.accessTokenEvent.observe( // 유저 조회 성공하면 액세스토큰 기기에 저장
            this, EventObserver { accessToken ->
                if (accessToken != null) {
                    ShortWeatherSharedPreference.setAccessToken(this, accessToken!!)
                }
            })
    }

    companion object {
        private const val SPLASH_TIME: Long = 2800
    }
}