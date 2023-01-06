package org.shortweather.presentation.input

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.R
import org.shortweather.databinding.ItemBottomSheetBinding

class BottomSheetAdapter(
    private val list: MutableList<BottomSheetItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<BottomSheetAdapter.Holder>() {
    private var itemList: MutableList<BottomSheetItem> = list
    private lateinit var binding: ItemBottomSheetBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = ItemBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.bind(item)

        binding.root.setOnClickListener {
            listener.onItemClick(position, item)
        }
    }

    inner class Holder(
        binding: ItemBottomSheetBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BottomSheetItem) {
            binding.tvContents.text = item.contents
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: BottomSheetItem)
    }
}

data class BottomSheetItem(var contents: String)