package org.shortweather.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivitySplashBinding
import org.shortweather.presentation.MainActivity
import org.shortweather.presentation.input.view.InputInfoActivity
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel
import org.shortweather.util.Constants.SUCCESS_200
import org.shortweather.util.Constants.FAILURE
import org.shortweather.util.Constants.HTTP_EXCEPTION_400
import org.shortweather.util.Constants.HTTP_EXCEPTION_401
import org.shortweather.util.Constants.HTTP_EXCEPTION_500
import org.shortweather.util.EventObserver
import org.shortweather.util.ShortWeatherSharedPreference
import org.shortweather.util.binding.BindingActivity
import org.shortweather.util.extension.showToast

@AndroidEntryPoint
class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val viewModel by viewModels<InputTimeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setDeviceToken(ShortWeatherSharedPreference.getDeviceToken(this)) // 디바이스 토큰 설정
        setObservers()
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.searchUser() // 디바이스 토큰을 서버에 전송하여 유저 조회
        }, SPLASH_TIME)
    }

    private fun setObservers() {
        viewModel.searchUserEvent.observe( // 유저 조회 성공 시 Main으로, 실패 시 정보입력으로
            this, EventObserver { code ->
                when (code) {
                    SUCCESS_200 -> {
                        if (viewModel.getIsExist()) {
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            startActivity(Intent(this, InputInfoActivity::class.java))
                        }
                        finish()
                    }
                    HTTP_EXCEPTION_400 -> showToast(getString(R.string.wait_server_error))
                    HTTP_EXCEPTION_401 -> showToast(getString(R.string.wait_server_error))
                    HTTP_EXCEPTION_500 -> showToast(getString(R.string.wait_server_error))
                    FAILURE -> showToast(getString(R.string.http_server_error))
                }
            })
        viewModel.accessTokenEvent.observe( // 유저 조회 성공하면 액세스토큰 기기에 저장
            this, EventObserver { accessToken ->
                if (accessToken != null) {
                    ShortWeatherSharedPreference.setAccessToken(this, accessToken)
                }
            })
    }

    companion object {
        private const val SPLASH_TIME: Long = 2800
    }
}