package org.shortweather.presentation.todayweather.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import org.shortweather.R
import org.shortweather.databinding.FragmentTodayWeatherBinding
import org.shortweather.presentation.todayweather.adapter.TodayWeatherAdapter
import org.shortweather.presentation.todayweather.viewmodel.TodayWeatherViewModel
import org.shortweather.util.binding.BindingFragment

class TodayWeatherFragment :
    BindingFragment<FragmentTodayWeatherBinding>(R.layout.fragment_today_weather) {
    private val viewModel: TodayWeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setOnClickListener()
        setAdapter()
        setOnRefreshListener()
    }

    private fun setOnRefreshListener() {
        binding.srlTodayWeather.setOnRefreshListener {
            binding.srlTodayWeather.isRefreshing = false
        }
    }

    private fun setOnClickListener() {
        binding.ivTodayWeatherInfo.setOnClickListener {
            viewModel.setToastEvent(true)
            Handler(Looper.getMainLooper()).postDelayed({ viewModel.setToastEvent(false) }, 2000)
        }
    }

    private fun setAdapter() {
        val adapter = TodayWeatherAdapter()
        adapter.submitList(viewModel.getTags())
        binding.rvTodayWeatherTag.adapter = adapter
    }
}