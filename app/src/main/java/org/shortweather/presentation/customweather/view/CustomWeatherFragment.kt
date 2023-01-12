package org.shortweather.presentation.customweather.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.FragmentCustomWeatherBinding
import org.shortweather.presentation.customweather.adapter.CustomWeatherPrecipitationAdapter
import org.shortweather.presentation.customweather.adapter.CustomWeatherTempAdapter
import org.shortweather.presentation.customweather.viewmodel.CustomWeatherViewModel
import org.shortweather.util.Constants.FINE_DUST
import org.shortweather.util.Constants.WEATHER
import org.shortweather.util.ShortWeatherSharedPreference
import org.shortweather.util.binding.BindingFragment

@AndroidEntryPoint
class CustomWeatherFragment :
    BindingFragment<FragmentCustomWeatherBinding>(R.layout.fragment_custom_weather) {
    private val viewModel: CustomWeatherViewModel by viewModels()
    private val tempAdapter = CustomWeatherTempAdapter()
    private val precipitationAdapter = CustomWeatherPrecipitationAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        setObserver()
        setOnClickListener()
        setOnScrollChangeListener()
    }

    private fun initView() {
        binding.rvCustomWeather.adapter = tempAdapter
        viewModel.getTemp()
        viewModel.getDetail(ShortWeatherSharedPreference.getAccessToken(requireContext()))
    }

    private fun setOnClickListener() {
        with(binding) {
            tvCustomWeatherSelectWeather.setOnClickListener {
                this@CustomWeatherFragment.viewModel.setTimeZoneWeather(WEATHER)
                rvCustomWeather.adapter = tempAdapter
                this@CustomWeatherFragment.viewModel.getTemp()
            }
            tvCustomWeatherSelectPrecipitation.setOnClickListener {
                this@CustomWeatherFragment.viewModel.setTimeZoneWeather(FINE_DUST)
                rvCustomWeather.adapter = precipitationAdapter
                this@CustomWeatherFragment.viewModel.getRain()
            }
        }
    }

    private fun setObserver() {
        viewModel.customWeatherTempList.observe(
            viewLifecycleOwner
        ) {
            tempAdapter.submitList(it)
        }
        viewModel.customWeatherRainList.observe(
            viewLifecycleOwner
        ) {
            precipitationAdapter.submitList(it)
        }
    }

    private fun setOnScrollChangeListener() {
        binding.svCustomWeather.setOnScrollChangeListener { it, _, _, _, _ ->
            if (!it.canScrollVertically(-1)) {
                binding.svCustomWeather.parent.requestDisallowInterceptTouchEvent(false)
            } else binding.svCustomWeather.parent.requestDisallowInterceptTouchEvent(true)
        }
    }
}