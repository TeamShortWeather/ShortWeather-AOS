package org.shortweather.presentation.customweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.data.model.CustomWeatherTemp
import org.shortweather.databinding.ItemCustomWeatherWeatherBinding
import org.shortweather.util.ItemDiffCallback

class CustomWeatherTempAdapter :
    ListAdapter<CustomWeatherTemp, CustomWeatherTempAdapter.CustomWeatherTempViewHolder>(
        ItemDiffCallback<CustomWeatherTemp>(
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem })
    ) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomWeatherTempViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return CustomWeatherTempViewHolder(
            ItemCustomWeatherWeatherBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomWeatherTempViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    class CustomWeatherTempViewHolder(
        private val binding: ItemCustomWeatherWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(responseTemp: CustomWeatherTemp) {
            binding.customWeatherTemp = responseTemp
            binding.executePendingBindings()
        }
    }
}
