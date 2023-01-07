package org.shortweather.presentation.customweather.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.shortweather.R
import org.shortweather.databinding.FragmentCustomWeatherBinding
import org.shortweather.presentation.customweather.adapter.CustomWeatherPrecipitationAdapter
import org.shortweather.presentation.customweather.adapter.CustomWeatherWeatherAdapter
import org.shortweather.presentation.customweather.viewmodel.CustomWeatherViewModel
import org.shortweather.util.Constants.FINE_DUST
import org.shortweather.util.Constants.WEATHER
import org.shortweather.util.binding.BindingFragment

class CustomWeatherFragment :
    BindingFragment<FragmentCustomWeatherBinding>(R.layout.fragment_custom_weather) {
    private val viewModel: CustomWeatherViewModel by viewModels()
    private val weatherAdapter = CustomWeatherWeatherAdapter()
    private val precipitationAdapter = CustomWeatherPrecipitationAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.rvCustomWeather.adapter = weatherAdapter
        weatherAdapter.submitList(viewModel.weatherList)
    }

    private fun setOnClickListener() {
        with(binding) {
            tvCustomWeatherSelectWeather.setOnClickListener {
                this@CustomWeatherFragment.viewModel.setTimeZoneWeather(WEATHER)
                rvCustomWeather.adapter = weatherAdapter
                weatherAdapter.submitList(this@CustomWeatherFragment.viewModel.weatherList)
            }
            tvCustomWeatherSelectPrecipitation.setOnClickListener {
                this@CustomWeatherFragment.viewModel.setTimeZoneWeather(FINE_DUST)
                rvCustomWeather.adapter = precipitationAdapter
                precipitationAdapter.submitList(this@CustomWeatherFragment.viewModel.precipitationList)
            }
        }
    }
}