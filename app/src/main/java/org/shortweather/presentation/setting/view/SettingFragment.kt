package org.shortweather.presentation.setting.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import org.shortweather.R
import org.shortweather.databinding.FragmentSettingBinding
import org.shortweather.presentation.alarmsetting.view.AlarmSettingActivity
import org.shortweather.presentation.othertimesetting.view.OtherTimeSettingActivity
import org.shortweather.presentation.waketimesetting.view.WakeTimeSettingActivity
import org.shortweather.util.binding.BindingFragment

class SettingFragment :
    BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
    }

    fun setOnClickListener() {
        binding.layoutSettingTime.setOnClickListener {
            val intent = Intent(context, OtherTimeSettingActivity::class.java)
            startActivity(intent)
        }
        binding.layoutSettingAlarm.setOnClickListener {
            val intent = Intent(context, AlarmSettingActivity::class.java)
            startActivity(intent)
        }
        binding.layoutSettingWakeUp.setOnClickListener {
            val intent = Intent(context, WakeTimeSettingActivity::class.java)
            startActivity(intent)
        }
    }

}