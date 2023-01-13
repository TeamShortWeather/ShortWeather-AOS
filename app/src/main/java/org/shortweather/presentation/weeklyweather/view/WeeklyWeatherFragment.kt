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
    private val adapter = WeeklyWeatherAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setAdapter()
        setOnRefreshListener()
    }

    private fun initView() {
        adapter.submitList(viewModel.getMockWeeklyWeatherList())
    }

    private fun setOnRefreshListener() {
        binding.srlWeeklyWeather.setOnRefreshListener {
            adapter.submitList(viewModel.getMockWeeklyWeatherList())
            binding.srlWeeklyWeather.isRefreshing = false
        }
    }

    private fun setAdapter() {
        binding.rvWeeklyWeather.adapter = adapter
    }
}