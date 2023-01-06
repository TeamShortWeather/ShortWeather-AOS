package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputInfoBinding
import org.shortweather.presentation.input.bottomsheet.BottomSheet
import org.shortweather.presentation.input.viewmodel.InputInfoViewModel
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class InputInfoActivity : BindingActivity<ActivityInputInfoBinding>(R.layout.activity_input_info) {
    val viewModel by viewModels<InputInfoViewModel>()
    val bottomSheetGender = BottomSheet("gender") // 3개의 바텀시트 객체 생성
    val bottomSheetAge = BottomSheet("age")
    val bottomSheetSense = BottomSheet("sense")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel // 데이터바인딩
        binding.lifecycleOwner = this

        binding.btnInputInfoNext.isEnabled = false // 최초에는 다음 버튼의 Enable 상태 false
        setOnClickListener()
        setObservers()
    }

    private fun setObservers() { // 뷰모델 관찰 돌입
        viewModel.isGenderSuccess.observe(this) {
            if (viewModel.isGenderSuccess.value == true) { // Success가 true = 아이템이 선택되었음을 의미
                bottomSheetGender.dismiss() // 바텀시트 소멸
                if (viewModel.isAgeSuccess.value == true && viewModel.isSenseSuccess.value == true) { // 만약 다른 두 정보가 모두 입력되었다면
                    binding.btnInputInfoNext.isEnabled = true // 버튼 활성화
                }
            } else { // 만약 하나라도 입력이 되어있지 않으면 버튼은 비활성화 상태 유지
                binding.btnInputInfoNext.isEnabled = false
            }
        } // 이하 동문
        viewModel.isAgeSuccess.observe(this) {
            if (viewModel.isAgeSuccess.value == true) {
                bottomSheetAge.dismiss()
                if (viewModel.isGenderSuccess.value == true && viewModel.isSenseSuccess.value == true) {
                    binding.btnInputInfoNext.isEnabled = true
                }
            } else {
                binding.btnInputInfoNext.isEnabled = false
            }
        }
        viewModel.isSenseSuccess.observe(this) {
            if (viewModel.isSenseSuccess.value == true) {
                bottomSheetSense.dismiss()
                if (viewModel.isAgeSuccess.value == true && viewModel.isGenderSuccess.value == true) {
                    binding.btnInputInfoNext.isEnabled = true
                }
            } else {
                binding.btnInputInfoNext.isEnabled = false
            }
        }
    }

    private fun setOnClickListener() {
        binding.btnInputInfoNext.setOnClickListener() { // 다음 버튼 클릭 시 InputTimeActivity로 이동,
            // intent로 선택한 3개의 값을 다음 activity에 전달해주어야함
            startActivity(Intent(this, InputTimeActivity::class.java))
            finish()
        }

        binding.layoutGender.setOnClickListener() { // 성별 선택란 클릭 시
            bottomSheetGender.show(supportFragmentManager, BottomSheet.TAG)
        }

        binding.layoutAge.setOnClickListener() { // 연령 선택란 클릭 시
            bottomSheetAge.show(supportFragmentManager, BottomSheet.TAG)
        }

        binding.layoutSense.setOnClickListener() { // 민감도 선택란 클릭 시
            bottomSheetSense.show(supportFragmentManager, BottomSheet.TAG)
        }
    }
}