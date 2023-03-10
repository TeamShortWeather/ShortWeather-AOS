package org.shortweather.presentation.todayweathercontainer.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.FragmentTodayWeatherContainerBinding
import org.shortweather.presentation.customweather.view.CustomWeatherFragment
import org.shortweather.presentation.todayweather.view.TodayWeatherFragment
import org.shortweather.presentation.todayweathercontainer.OnPageDownClickListener
import org.shortweather.presentation.todayweathercontainer.adapter.TodayWeatherContainerAdapter
import org.shortweather.presentation.todayweathercontainer.viewmodel.TodayWeatherContainerViewModel
import org.shortweather.util.binding.BindingFragment

@AndroidEntryPoint
class TodayWeatherContainerFragment :
    BindingFragment<FragmentTodayWeatherContainerBinding>(R.layout.fragment_today_weather_container) {
    private val viewModel: TodayWeatherContainerViewModel by viewModels()
    private val todayWeatherFragment = TodayWeatherFragment()
    private val customWeatherFragment = CustomWeatherFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setOnPageDownClickListener()
    }

    private fun setAdapter() {
        binding.vpTodayWeatherContainer.adapter = TodayWeatherContainerAdapter(
            listOf(todayWeatherFragment, customWeatherFragment),
            requireActivity()
        )
    }

    private fun setOnPageDownClickListener() {
        todayWeatherFragment.setOnPageDownClickListener(object : OnPageDownClickListener {
            override fun pageDown() {
                binding.vpTodayWeatherContainer.currentItem = 1
            }
        })
    }
}