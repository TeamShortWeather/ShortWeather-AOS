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
import org.shortweather.presentation.todayweathercontainer.OnPageDownClickListener
import org.shortweather.util.Constants.FAILURE
import org.shortweather.util.Constants.HTTP_EXCEPTION_400
import org.shortweather.util.Constants.HTTP_EXCEPTION_401
import org.shortweather.util.Constants.HTTP_EXCEPTION_500
import org.shortweather.util.EventObserver
import org.shortweather.util.ShortWeatherSharedPreference
import org.shortweather.util.binding.BindingFragment
import org.shortweather.util.extension.showToast

@AndroidEntryPoint
class TodayWeatherFragment :
    BindingFragment<FragmentTodayWeatherBinding>(R.layout.fragment_today_weather) {
    private val viewModel: TodayWeatherViewModel by viewModels()
    private lateinit var listener: OnPageDownClickListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        setOnRefreshListener()
        setOnClickListener()
        setObservers()
        setAdapter()
    }

    fun setOnPageDownClickListener(listener: OnPageDownClickListener) {
        this.listener = listener
    }

    private fun initView() {
        viewModel.getTodayWeatherInfo(ShortWeatherSharedPreference.getAccessToken(requireContext()))
    }

    private fun setOnRefreshListener() {
        binding.srlTodayWeather.setOnRefreshListener {
            viewModel.getTodayWeatherInfo(
                ShortWeatherSharedPreference.getAccessToken(requireContext()),
                true
            )
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
        binding.ivTodayWeatherExpandDown.setOnClickListener {
            listener.pageDown()
        }
    }

    private fun setObservers() {
        viewModel.refreshEvent.observe(viewLifecycleOwner, EventObserver { isRefresh ->
            if (isRefresh) {
                binding.srlTodayWeather.isRefreshing = false
            }
        })
        viewModel.todayWeatherInfoEvent.observe(viewLifecycleOwner, EventObserver { code ->
            when (code) {
                HTTP_EXCEPTION_400 -> requireContext().showToast(getString(R.string.wait_server_error))
                HTTP_EXCEPTION_401 -> requireContext().showToast(getString(R.string.wait_server_error))
                HTTP_EXCEPTION_500 -> requireContext().showToast(getString(R.string.wait_server_error))
                FAILURE -> requireContext().showToast(getString(R.string.http_server_error))
            }
        })
        viewModel.todayWeatherToastInfoEvent.observe(viewLifecycleOwner, EventObserver { code ->
            when (code) {
                HTTP_EXCEPTION_400 -> requireContext().showToast(getString(R.string.wait_server_error))
                HTTP_EXCEPTION_401 -> requireContext().showToast(getString(R.string.wait_server_error))
                HTTP_EXCEPTION_500 -> requireContext().showToast(getString(R.string.wait_server_error))
                FAILURE -> requireContext().showToast(getString(R.string.http_server_error))
            }
        })
    }

    private fun setAdapter() {
        val adapter = TodayWeatherAdapter()
        binding.rvTodayWeatherTag.adapter = adapter
        viewModel.tags.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}