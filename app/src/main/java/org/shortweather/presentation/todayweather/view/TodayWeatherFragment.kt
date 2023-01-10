package org.shortweather.presentation.todayweather.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.FragmentTodayWeatherBinding
import org.shortweather.presentation.todayweather.adapter.TodayWeatherAdapter
import org.shortweather.presentation.todayweather.viewmodel.TodayWeatherViewModel
import org.shortweather.util.ShortWeatherSharedPreference
import org.shortweather.util.binding.BindingFragment

@AndroidEntryPoint
class TodayWeatherFragment :
    BindingFragment<FragmentTodayWeatherBinding>(R.layout.fragment_today_weather) {
    private val viewModel: TodayWeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getTodayWeatherInfo(ShortWeatherSharedPreference.getAccessToken(requireContext()))
        setOnClickListener()
        setOnRefreshListener()
        setAdapter()
    }

    private fun setOnRefreshListener() {
        binding.srlTodayWeather.setOnRefreshListener {
            binding.srlTodayWeather.isRefreshing = false
        }
    }

    private fun setOnClickListener() {
        binding.ivTodayWeatherInfo.setOnClickListener {
            viewModel.getTodayWeatherToastInfo(
                ShortWeatherSharedPreference.getAccessToken(
                    requireContext()
                )
            )
            Handler(Looper.getMainLooper()).postDelayed({ viewModel.setToastEvent(false) }, 2000)
        }
    }

    private fun setAdapter() {
        val adapter = TodayWeatherAdapter()
        binding.rvTodayWeatherTag.adapter = adapter
        viewModel.tags.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}