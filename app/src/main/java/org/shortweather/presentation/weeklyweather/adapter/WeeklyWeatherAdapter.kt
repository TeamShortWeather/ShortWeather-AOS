package org.shortweather.presentation.weeklyweather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.R
import org.shortweather.data.source.remote.ResponseWeeklyWeather
import org.shortweather.databinding.ItemWeeklyWeatherBinding
import org.shortweather.databinding.ItemWeeklyWeatherHeaderBinding

class WeeklyWeatherAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private val weeklyList = mutableListOf<ResponseWeeklyWeather?>()

    class WeeklyWeatherViewHolder(private val binding: ItemWeeklyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(weather: ResponseWeeklyWeather) {
            binding.tvWeeklyWeatherCalendarWeek.text = weather.weekly_weather_calender_week
            binding.tvWeeklyWeatherCalendarDay.text = weather.weekly_weather_calendar_day
            binding.ivWeeklyWeatherDayInfo.setImageResource(R.drawable.ic_weekly_weather_example)
            binding.tvWeeklyWeatherDayInfo.text = weather.weekly_weather_day_rain
            binding.ivWeeklyWeatherNightInfo.setImageResource(R.drawable.ic_weekly_weather_example)
            binding.tvWeeklyWeatherNightInfo.text = weather.weekly_weather_day_rain
            binding.tvWeeklyWeatherTempUp.text = weather.weekly_weather_temp_up
            binding.tvWeeklyWeatherTempDown.text = weather.weekly_weather_temp_down
        }
    }

    class HeadViewHolder(
        private val binding: ItemWeeklyWeatherHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER ->
                HeadViewHolder(
                    ItemWeeklyWeatherHeaderBinding.inflate(inflater, parent, false)
                )
            else -> WeeklyWeatherViewHolder(
                ItemWeeklyWeatherBinding.inflate(inflater, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (weeklyList[position] == null) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeeklyWeatherViewHolder -> holder.onBind(weeklyList[position] as ResponseWeeklyWeather)
            else -> {}
        }
    }

    override fun getItemCount(): Int = weeklyList.size

    fun setWeatherList(weeklyList: List<ResponseWeeklyWeather?>) {
        this.weeklyList.addAll(weeklyList)
        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }
}