package org.shortweather.presentation.alarmsetting.view

import android.os.Bundle
import androidx.core.view.isVisible
import org.shortweather.R
import org.shortweather.databinding.ActivityAlarmSettingBinding
import org.shortweather.util.binding.BindingActivity

class AlarmSettingActivity :
    BindingActivity<ActivityAlarmSettingBinding>(R.layout.activity_alarm_setting) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this

        binding.btnAlarmSettingAll.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                binding.layoutAlarmSettingWake.isVisible = true
                binding.layoutAlarmSettingSleep.isVisible = true
                binding.layoutAlarmSettingNews.isVisible = true
            } else {
                binding.layoutAlarmSettingWake.isVisible = false
                binding.layoutAlarmSettingSleep.isVisible = false
                binding.layoutAlarmSettingNews.isVisible = false
            }
        }
    }
}

