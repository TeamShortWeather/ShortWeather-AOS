package org.shortweather.presentation.todayweathercontainer.view

import android.os.Bundle
import android.view.View
import org.shortweather.R
import org.shortweather.databinding.FragmentTodayWeatherContainerBinding
import org.shortweather.presentation.customweather.view.CustomWeatherFragment
import org.shortweather.presentation.todayweather.view.TodayWeatherFragment
import org.shortweather.presentation.todayweathercontainer.adapter.TodayWeatherContainerAdapter
import org.shortweather.util.binding.BindingFragment

class TodayWeatherContainerFragment :
    BindingFragment<FragmentTodayWeatherContainerBinding>(R.layout.fragment_today_weather_container) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpTodayWeatherContainer.adapter = TodayWeatherContainerAdapter(
            listOf(TodayWeatherFragment(), CustomWeatherFragment()),
            requireActivity()
        )
    }
}