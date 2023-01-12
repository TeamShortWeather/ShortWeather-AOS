package org.shortweather.presentation.weeklyweather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.data.model.WeeklyWeather
import org.shortweather.databinding.ItemWeeklyWeatherBinding

class WeeklyWeatherAdapter :
    RecyclerView.Adapter<WeeklyWeatherAdapter.WeeklyWeatherViewHolder>() {
    private lateinit var inflater: LayoutInflater
    private val weeklyList = mutableListOf<WeeklyWeather>()

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
        holder.onBind(weeklyList[position])
    }

    override fun getItemCount(): Int = weeklyList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setWeatherList(weeklyList: List<WeeklyWeather>) {
        this.weeklyList.addAll(weeklyList)
        notifyDataSetChanged()
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