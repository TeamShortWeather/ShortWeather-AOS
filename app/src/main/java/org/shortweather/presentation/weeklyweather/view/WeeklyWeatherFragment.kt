package org.shortweather.presentation.weeklyweather.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.shortweather.R
import org.shortweather.databinding.FragmentWeeklyWeatherBinding
import org.shortweather.presentation.weeklyweather.adapter.WeeklyWeatherAdapter
import org.shortweather.presentation.weeklyweather.viewmodel.WeeklyWeatherViewModel
import org.shortweather.util.binding.BindingFragment

class WeeklyWeatherFragment :
    BindingFragment<FragmentWeeklyWeatherBinding>(R.layout.fragment_weekly_weather) {
    private val viewModel by viewModels<WeeklyWeatherViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() {
        val adapter = WeeklyWeatherAdapter()
        adapter.setWeatherList(viewModel.getMockWeeklyWeatherList())
        binding.rvWeeklyWeather.adapter = adapter
    }
}