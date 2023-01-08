package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputInfoBinding
import org.shortweather.presentation.input.viewmodel.InputInfoViewModel
import org.shortweather.util.binding.BindingActivity


@AndroidEntryPoint
class InputInfoActivity : BindingActivity<ActivityInputInfoBinding>(R.layout.activity_input_info) {
    private val viewModel by viewModels<InputInfoViewModel>()
    private val bottomSheetGender = BottomSheetFragment.newInstance("gender") // 3개의 바텀시트 객체 생성
    private val bottomSheetAge = BottomSheetFragment.newInstance("age")
    private val bottomSheetSense = BottomSheetFragment.newInstance("sense")
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel // 데이터바인딩
        binding.lifecycleOwner = this
        binding.btnInputInfoNext.isEnabled = false // 최초에는 다음 버튼의 Enable 상태 false
        setOnClickListener()
        setObservers()
        val sharedPreferences = getSharedPreferences("sFile1", MODE_PRIVATE) //저장된 토큰을 불러오기 위한 셋팅
        token =
            sharedPreferences.getString("Token1", token).toString() //key값과 value값으로 구분된 저장된 토큰값을 불러옵니다.
        Log.d("token", "token: ".plus(token))
    }

    override fun onStop() {
        super.onStop()
        // Activity가 종료되기 전에 저장한다.
        //SharedPreferences를 sFile이름, 기본모드로 설정
        val sharedPreferences = getSharedPreferences("sFile1", MODE_PRIVATE)
        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        val editor = sharedPreferences.edit()
        editor.putString("Token1", token) // key, value를 이용하여 저장하는 형태
        editor.commit()
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
            val intent = Intent(this, InputTimeActivity::class.java)
            intent.putExtra("devicetoken", token)
            intent.putExtra("gender", viewModel.inputGender.value)
            intent.putExtra("age", viewModel.inputAge.value)
            intent.putExtra("sense", viewModel.inputSense.value)
            startActivity(Intent(intent))
        }

        binding.layoutGender.setOnClickListener() { // 성별 선택란 클릭 시
            binding.vInputInfoGenderLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputInfoGender.setTextColor(R.color.short_weather_black)
            binding.btnGender.setBackgroundResource(R.drawable.ic_expand)
            bottomSheetGender.show(supportFragmentManager, BottomSheetFragment.TAG)
        }

        binding.layoutAge.setOnClickListener() { // 연령 선택란 클릭 시
            binding.vInputInfoAgeLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputInfoAge.setTextColor(R.color.short_weather_black)
            binding.btnAge.setBackgroundResource(R.drawable.ic_expand)
            bottomSheetAge.show(supportFragmentManager, BottomSheetFragment.TAG)
        }

        binding.layoutSense.setOnClickListener() { // 민감도 선택란 클릭 시
            binding.vInputInfoSenseLine.setBackgroundResource(R.color.short_weather_blue)
            binding.tvInputInfoSense.setTextColor(R.color.short_weather_black)
            binding.btnSense.setBackgroundResource(R.drawable.ic_expand)
            bottomSheetSense.show(supportFragmentManager, BottomSheetFragment.TAG)
        }
    }

}