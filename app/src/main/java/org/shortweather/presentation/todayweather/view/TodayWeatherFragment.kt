package org.shortweather.presentation.todayweather.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.FragmentTodayWeatherBinding
import org.shortweather.presentation.todayweather.adapter.TodayWeatherAdapter
import org.shortweather.presentation.todayweather.viewmodel.TodayWeatherViewModel
import org.shortweather.util.binding.BindingFragment

@AndroidEntryPoint
class TodayWeatherFragment :
    BindingFragment<FragmentTodayWeatherBinding>(R.layout.fragment_today_weather) {
    private val viewModel: TodayWeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnRefreshListener()
        setAdapter()
    }

    private fun setOnRefreshListener() {
        binding.srlTodayWeather.setOnRefreshListener {
            binding.srlTodayWeather.isRefreshing = false
        }
    }

    private fun setAdapter() {
        val adapter = TodayWeatherAdapter()
        adapter.submitList(viewModel.getTags())
        binding.rvTodayWeatherTag.adapter = adapter
    }
}