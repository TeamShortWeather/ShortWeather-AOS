package org.shortweather.presentation.todayweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.R
import org.shortweather.data.model.FineDust
import org.shortweather.data.model.Newsflash
import org.shortweather.data.model.TodayWeatherTag
import org.shortweather.data.model.UltrafineDust
import org.shortweather.databinding.ItemTodayWeatherFineDustBinding
import org.shortweather.databinding.ItemTodayWeatherNewsflashBinding
import org.shortweather.databinding.ItemTodayWeatherUltrafineDustBinding
import org.shortweather.util.ItemDiffCallback

class TodayWeatherAdapter : ListAdapter<TodayWeatherTag, RecyclerView.ViewHolder>(
    ItemDiffCallback<TodayWeatherTag>(
        onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem })
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return when (viewType) {
            VIEW_TYPE_NEWSFLASH -> NewsflashViewHolder(
                ItemTodayWeatherNewsflashBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            VIEW_TYPE_FINE_DUST -> FineDustViewHolder(
                ItemTodayWeatherFineDustBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> UltrafineDustViewHolder(
                ItemTodayWeatherUltrafineDustBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsflashViewHolder -> {
                holder.onBind(currentList[position] as Newsflash)
            }
            is FineDustViewHolder -> {
                holder.onBind(currentList[position] as FineDust)
            }
            is UltrafineDustViewHolder -> {
                holder.onBind(currentList[position] as UltrafineDust)
            }
            else -> throw IllegalArgumentException("Not found.")
        }
    }

    override fun getItemViewType(position: Int): Int = when (currentList[position]) {
        is Newsflash -> VIEW_TYPE_NEWSFLASH
        is FineDust -> VIEW_TYPE_FINE_DUST
        else -> VIEW_TYPE_ULTRAFINE_DUST
    }

    class NewsflashViewHolder(private val binding: ItemTodayWeatherNewsflashBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(newsflash: Newsflash) {
            binding.newsflash = newsflash
            when (newsflash.description) {
                in redBackground -> binding.tvTodayWeatherNewsflash.setBackgroundResource(R.drawable.bg_red_newsflash)
                in blueBackground -> binding.tvTodayWeatherNewsflash.setBackgroundResource(R.drawable.bg_blue_newsflash)
                in yellowBackground -> binding.tvTodayWeatherNewsflash.setBackgroundResource(R.drawable.bg_yellow_newsflash)
            }
        }
    }

    class FineDustViewHolder(private val binding: ItemTodayWeatherFineDustBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(fineDust: FineDust) {
            binding.fineDust = fineDust
        }
    }

    class UltrafineDustViewHolder(private val binding: ItemTodayWeatherUltrafineDustBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(ultrafineDust: UltrafineDust) {
            binding.ultrafineDust = ultrafineDust
        }
    }

    companion object {
        private const val VIEW_TYPE_NEWSFLASH = 0
        private const val VIEW_TYPE_FINE_DUST = 1
        private const val VIEW_TYPE_ULTRAFINE_DUST = 2
        private val redBackground = setOf("폭염특보")
        private val blueBackground = setOf("대설특보", "한파특보", "폭풍해일특보", "태풍특보", "호우특보", "강풍특보", "풍랑특보")
        private val yellowBackground = setOf("황사특보", "미세먼지특보", "건조특보")
    }
}