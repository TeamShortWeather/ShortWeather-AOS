package org.shortweather.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityMainBinding
import org.shortweather.presentation.todayweathercontainer.view.TodayWeatherContainerFragment
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setNavigation()
        startTargetFragment(TodayWeatherContainerFragment())
    }

    private fun initView() {
        binding.nvMain.itemIconTintList = null
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
}