package org.shortweather.presentation.weeklyweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.data.model.WeeklyWeather
import org.shortweather.databinding.ItemWeeklyWeatherBinding
import org.shortweather.util.ItemDiffCallback

class WeeklyWeatherAdapter :
    ListAdapter<WeeklyWeather, WeeklyWeatherAdapter.WeeklyWeatherViewHolder>(
        ItemDiffCallback<WeeklyWeather>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem })
    ) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeeklyWeatherViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return WeeklyWeatherViewHolder(
            ItemWeeklyWeatherBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeeklyWeatherViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class WeeklyWeatherViewHolder(
        private val binding: ItemWeeklyWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(weather: WeeklyWeather) {
            binding.weeklyWeather = weather
            binding.executePendingBindings()
        }
    }
}