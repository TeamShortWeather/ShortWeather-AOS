package org.shortweather.presentation.todayweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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
        }
    }

    class FineDustViewHolder(private val binding: ItemTodayWeatherFineDustBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(fineDust: FineDust) {
            // 미세먼지 수치에 따라 background 분기 처리 로직 필요
        }
    }

    class UltrafineDustViewHolder(private val binding: ItemTodayWeatherUltrafineDustBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(ultraDust: UltrafineDust) {
            // 초미세먼지 수치에 따라 background 분기 처리 로직 필요
        }
    }

    companion object {
        private const val VIEW_TYPE_NEWSFLASH = 0
        private const val VIEW_TYPE_FINE_DUST = 1
        private const val VIEW_TYPE_ULTRAFINE_DUST = 2
    }
}