package org.shortweather.presentation.splash

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import android.os.Handler
import android.os.Looper
import org.shortweather.databinding.ActivitySplashBinding
import org.shortweather.presentation.MainActivity
import org.shortweather.presentation.input.view.InputInfoActivity
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class SplashActivity: BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val SPLASH_TIME_OUT: Long = 3000  //3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            //main activity 시작
            startActivity(Intent(this, InputInfoActivity::class.java))

            //액티비티 닫기
            finish()
        }, SPLASH_TIME_OUT)
    }
}