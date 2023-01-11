package org.shortweather.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityMainBinding
import org.shortweather.presentation.customweather.view.CustomWeatherFragment
import org.shortweather.presentation.setting.view.SettingFragment
import org.shortweather.presentation.todayweathercontainer.view.TodayWeatherContainerFragment
import org.shortweather.presentation.weeklyweather.view.WeeklyWeatherFragment
import org.shortweather.util.binding.BindingActivity
import org.shortweather.util.extension.replace

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setNavigationItemSelectedListener()
        setNavigation()
        startTargetFragment(R.id.today_weather_menu_drawer)
    }

    private fun initView() {
        binding.nvMain.itemIconTintList = null
        startTargetFragment(TodayWeatherContainerFragment())
    }

    private fun setNavigationItemSelectedListener() {
        binding.nvMain.setNavigationItemSelectedListener { menuItem ->
            startTargetFragment(menuItem.itemId)
            binding.dlMain.close()
            true
        }
    }

    private fun setNavigation() {
        binding.tbMain.setNavigationOnClickListener {
            binding.dlMain.open()
        }
    }

    private fun startTargetFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commitAllowingStateLoss()
    }

    private fun startTargetFragment(itemId: Int) = when (itemId) {
        R.id.today_weather_menu_drawer -> replace<TodayWeatherContainerFragment>(R.id.fcv_main)
        R.id.weekly_weather_menu_drawer -> replace<WeeklyWeatherFragment>(R.id.fcv_main)
        R.id.clothes_menu_drawer -> {}
        R.id.setting_menu_drawer -> replace<SettingFragment>(R.id.fcv_main)
        else -> throw IllegalArgumentException("Not found error.")
    }
}