package org.shortweather.presentation.input.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.databinding.ItemBottomSheetBinding

class BottomSheetAdapter(
    private val list: MutableList<BottomSheetItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<BottomSheetAdapter.InfoItemHolder>() {
    private var itemList: MutableList<BottomSheetItem> = list
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoItemHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return InfoItemHolder(ItemBottomSheetBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: InfoItemHolder, position: Int) {
        val item = itemList[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int = itemList.size

    inner class InfoItemHolder(
        private val binding: ItemBottomSheetBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BottomSheetItem) {
            binding.root.setOnClickListener {
                listener.onItemClick(item)
            }
            binding.tvContents.text = item.contents
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: BottomSheetItem)
    }
}

data class BottomSheetItem(var contents: String)