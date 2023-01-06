package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputTimeBinding
import org.shortweather.presentation.MainActivity
import org.shortweather.presentation.input.bottomsheet.BottomSheet
import org.shortweather.presentation.input.bottomsheet.BottomSheetTime
import org.shortweather.presentation.input.viewmodel.InputInfoViewModel
import org.shortweather.presentation.input.viewmodel.InputTimeViewModel
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class InputTimeActivity : BindingActivity<ActivityInputTimeBinding>(R.layout.activity_input_time) {
    val viewModel by viewModels<InputTimeViewModel>()
    private val bottomSheetWake = BottomSheetTime("wake") // 3개의 바텀시트 객체 생성
    private val bottomSheetOut = BottomSheetTime("out")
    private val bottomSheetReturn = BottomSheetTime("return")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnInputTimeCheck.isEnabled = false // 최초에는 확인 버튼의 Enable 상태 false
        setOnClickListener()
    }

    private fun setOnClickListener() {
        // 확인 버튼을 누르면 MainActivity로 이동
        binding.btnInputTimeCheck.setOnClickListener() { // 메인 화면으로 이동
            startActivity(Intent(this, MainActivity::class.java)) // 서버에 전달해주는 로직 추후에 필요
            finish()
        }

        binding.layoutTimeWake.setOnClickListener() { // 기상시간 선택
            bottomSheetWake.show(supportFragmentManager, BottomSheetTime.TAG)
        }

        binding.layoutTimeOut.setOnClickListener() { // 외출시간 선택
            bottomSheetOut.show(supportFragmentManager, BottomSheetTime.TAG)
        }

        binding.layoutTimeReturn.setOnClickListener() { // 귀가시간 선택
            bottomSheetReturn.show(supportFragmentManager, BottomSheetTime.TAG)
        }
    }

}