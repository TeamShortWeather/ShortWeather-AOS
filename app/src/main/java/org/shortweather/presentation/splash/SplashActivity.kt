package org.shortweather.presentation.splash

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import android.os.Handler
import android.os.Looper
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
    private val SPLASH_TIME: Long = 3000  //3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setDeviceToken("afterlike") // 테스트를 위한 가상의 디바이스 토큰 설정
        // viewModel.setDeviceToken(ShortWeatherSharedPreference.getToken(this)) // 디바이스 토큰 설정
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.searchUser() // 디바이스 토큰를 포함한 서버 요청을 통한 유저 조회
            setObservers() // 유저 조회 관련 LiveData 관찰
        }, SPLASH_TIME)
    }

    private fun setObservers() {
        viewModel.searchUserEvent.observe(
            this@SplashActivity, EventObserver { isSuccess ->
                if (isSuccess) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, InputInfoActivity::class.java))
                    finish()
                }
            })
    }

}