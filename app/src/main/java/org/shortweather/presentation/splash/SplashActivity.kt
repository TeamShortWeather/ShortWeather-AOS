package org.shortweather.presentation.splash

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import android.os.Handler
import android.os.Looper
import android.util.Log
import org.shortweather.databinding.ActivitySplashBinding
import org.shortweather.presentation.MainActivity
import org.shortweather.presentation.input.view.InputInfoActivity
import org.shortweather.util.TokenSharedPreference
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class SplashActivity: BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val SPLASH_TIME: Long = 3000  //3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            //if(서버에 디바이스 토큰을 전송한 상태가 아니라면 false 처리되는 flag){
                startActivity(Intent(this, InputInfoActivity::class.java))
                finish()
            /*} else {
                startActivity(Intent(this, InputInfoActivity::class.java))
                finish()
            }*/
        }, SPLASH_TIME)
    }
}