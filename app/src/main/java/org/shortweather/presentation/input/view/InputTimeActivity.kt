package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputTimeBinding
import org.shortweather.presentation.MainActivity
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class InputTimeActivity: BindingActivity<ActivityInputTimeBinding>(R.layout.activity_input_time) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setOnClickListener() {
        // 확인 버튼을 누르면 MainActivity로 이동
        binding.btnInputTimeCheck.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java)) // 서버에 전달해주는 로직 추후에 필요
            finish()
        }
    }

}