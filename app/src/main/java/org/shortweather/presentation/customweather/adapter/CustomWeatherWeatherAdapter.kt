package org.shortweather.presentation.customweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.data.model.CustomWeatherWeather
import org.shortweather.databinding.ItemCustomWeatherWeatherBinding
import org.shortweather.util.ItemDiffCallback

class CustomWeatherWeatherAdapter :
    ListAdapter<CustomWeatherWeather, CustomWeatherWeatherAdapter.CustomWeatherWeatherViewHolder>(
        ItemDiffCallback<CustomWeatherWeather>(
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem })
    ) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomWeatherWeatherViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return CustomWeatherWeatherViewHolder(
            ItemCustomWeatherWeatherBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomWeatherWeatherViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class CustomWeatherWeatherViewHolder(private val binding: ItemCustomWeatherWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(customWeatherWeather: CustomWeatherWeather) {
            binding.customWeatherWeather = customWeatherWeather
        }
    }
}