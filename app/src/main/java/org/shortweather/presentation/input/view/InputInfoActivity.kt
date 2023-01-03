package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputInfoBinding
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class InputInfoActivity: BindingActivity<ActivityInputInfoBinding>(R.layout.activity_input_info) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        // 다음 버튼을 누르면 InputTimeActivity로 이동
        binding.btnInputInfoNext.setOnClickListener(){ // 다음 버튼 클릭 시
            startActivity(Intent(this, InputTimeActivity::class.java))
            finish()
        }
        binding.layoutGender.setOnClickListener(){ // 성별 선택란 클릭 시
/*            val list = mutableListOf(
                BottomSheetItem("여자"),
                BottomSheetItem("남자"),
            )
            val adapter = BottomSheetAdapter()
            adapter.setItem(list)
            val modalBottomSheet = BottomSheetGender(adapter)
            val modalBottomSheet = BottomSheetGender()
            modalBottomSheet.show(supportFragmentManager, BottomSheetGender.TAG) */
        }
        binding.layoutAge.setOnClickListener(){ // 나이 선택란 클릭 시

        }
        binding.layoutSense.setOnClickListener(){ // 민감도 선택란 클릭 시

        }
    }
}