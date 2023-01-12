package org.shortweather.presentation.weeklyweather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.R
import org.shortweather.data.model.WeeklyWeather
import org.shortweather.databinding.ItemWeeklyWeatherBinding
import org.shortweather.databinding.ItemWeeklyWeatherHeaderBinding

class WeeklyWeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var inflater: LayoutInflater
    private val weeklyList = mutableListOf<WeeklyWeather?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return when (viewType) {
            VIEW_TYPE_HEADER ->
                WeeklyWeatherHeaderViewHolder(
                    ItemWeeklyWeatherHeaderBinding.inflate(inflater, parent, false)
                )
            else -> WeeklyWeatherViewHolder(
                ItemWeeklyWeatherBinding.inflate(inflater, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeeklyWeatherViewHolder -> holder.onBind(weeklyList[position] as WeeklyWeather)
            else -> {}
        }
    }

    override fun getItemCount(): Int = weeklyList.size

    override fun getItemViewType(position: Int): Int =
        if (weeklyList[position] == null) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM

    @SuppressLint("NotifyDataSetChanged")
    fun setWeatherList(weeklyList: List<WeeklyWeather?>) {
        this.weeklyList.addAll(weeklyList)
        notifyDataSetChanged()
    }

    class WeeklyWeatherViewHolder(private val binding: ItemWeeklyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(weather: WeeklyWeather) {
            binding.weeklyWeather = weather
            binding.executePendingBindings()
            binding.ivWeeklyWeatherDayInfo.setImageResource(R.drawable.ic_weekly_weather_example)
            binding.ivWeeklyWeatherNightInfo.setImageResource(R.drawable.ic_weekly_weather_example)
        }
    }

    class WeeklyWeatherHeaderViewHolder(
        private val binding: ItemWeeklyWeatherHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }
}