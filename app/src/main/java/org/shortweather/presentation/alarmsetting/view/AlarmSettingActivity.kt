package org.shortweather.presentation.alarmsetting.view

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityAlarmSettingBinding
import org.shortweather.presentation.alarmsetting.viewmodel.AlarmSettingViewModel
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class AlarmSettingActivity :
    BindingActivity<ActivityAlarmSettingBinding>(R.layout.activity_alarm_setting) {
    private val viewModel by viewModels<AlarmSettingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView()
        setOnCheckedChangeListener()
    }

    private fun initView(){
        binding.btnAlarmSettingAll.isChecked = true
        binding.btnAlarmSettingWake.isChecked = true
    }

    private fun setOnCheckedChangeListener() {
        binding.btnAlarmSettingAll.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.setIsChange(true)
            } else {
                viewModel.setIsChange(false)
            }
        }
    }
}

