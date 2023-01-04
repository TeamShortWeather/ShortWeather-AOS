package org.shortweather.presentation.input.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputInfoBinding
import org.shortweather.presentation.input.BottomSheetAdapter
import org.shortweather.presentation.input.BottomSheetItem
import org.shortweather.presentation.input.bottomsheet.BottomSheetGender
import org.shortweather.presentation.input.viewmodel.InputInfoViewModel
import org.shortweather.util.binding.BindingActivity
import javax.inject.Inject

@AndroidEntryPoint
class InputInfoActivity: BindingActivity<ActivityInputInfoBinding>(R.layout.activity_input_info) {
    private val viewModel by viewModels<InputInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.btnInputInfoNext.isEnabled = false // 최초에는 Enable 상태
        setOnClickListener()
        setObservers()
    }

    private fun setObservers(){
        viewModel.
    }

    private fun setOnClickListener() {
        // 다음 버튼을 누르면 InputTimeActivity로 이동
        binding.btnInputInfoNext.setOnClickListener(){ // 다음 버튼 클릭 시
            startActivity(Intent(this, InputTimeActivity::class.java))
            finish()
        }

        binding.layoutGender.setOnClickListener(){ // 성별 선택란 클릭 시
            val list = mutableListOf(
                BottomSheetItem("여자"),
                BottomSheetItem("남자"),
            )
            val adapter = BottomSheetAdapter(list, object: BottomSheetAdapter.OnItemClickListener {
                override fun onItemClick(position: Int, item: BottomSheetItem) {
                    Log.d("tag", "여기까지 진행됨, $position")
                    binding.tvInputInfoGenderContents.text = item.contents
                }
            })
            val bottomSheet = BottomSheetGender("gender", adapter)
            bottomSheet.show(supportFragmentManager, BottomSheetGender.TAG)
        }

        /*binding.layoutAge.setOnClickListener(){ // 나이 선택란 클릭 시
            val list = mutableListOf(
                BottomSheetItem("10대"),
                BottomSheetItem("20대"),
                BottomSheetItem("30대"),
                BottomSheetItem("40대"),
                BottomSheetItem("50대"),
                BottomSheetItem("60대 이상"),
            )
            val adapter = BottomSheetAdapter()
            adapter.setItem(list)
            BottomSheetGender("age", adapter).show(supportFragmentManager, BottomSheetGender.TAG)
        }
        binding.layoutSense.setOnClickListener(){ // 민감도 선택란 클릭 시
            val list = mutableListOf(
                BottomSheetItem("추위를 잘 타요"),
                BottomSheetItem("보통이에요"),
                BottomSheetItem("더위를 잘 타요"),
                BottomSheetItem("추위, 더위 모두 타요"),
            )
            val adapter = BottomSheetAdapter()
            adapter.setItem(list)
            BottomSheetGender("sense", adapter).show(supportFragmentManager, BottomSheetGender.TAG)
        }*/
    }
}