package org.shortweather.presentation.customweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.data.model.CustomWeatherPrecipitation
import org.shortweather.databinding.ItemCustomWeatherPrecipitationBinding
import org.shortweather.util.ItemDiffCallback

class CustomWeatherPrecipitationAdapter :
    ListAdapter<CustomWeatherPrecipitation, CustomWeatherPrecipitationAdapter.CustomWeatherPrecipitationViewHolder>(
        ItemDiffCallback<CustomWeatherPrecipitation>(
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem })
    ) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomWeatherPrecipitationViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return CustomWeatherPrecipitationViewHolder(
            ItemCustomWeatherPrecipitationBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomWeatherPrecipitationViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class CustomWeatherPrecipitationViewHolder(
        private val binding: ItemCustomWeatherPrecipitationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(customWeatherPrecipitation: CustomWeatherPrecipitation) {
            binding.customWeatherPrecipitation = customWeatherPrecipitation
        }
    }
}