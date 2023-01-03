package org.shortweather.presentation.input

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.shortweather.R
import org.shortweather.databinding.ItemBottomSheetBinding

class BottomSheetAdapter : RecyclerView.Adapter<BottomSheetAdapter.Holder>() {
    private var itemList: MutableList<BottomSheetItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bottom_sheet, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    fun setItem(items: MutableList<BottomSheetItem>) {
        if (!items.isNullOrEmpty()) {
            itemList = items
            notifyDataSetChanged()
        }
    }

    inner class Holder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: BottomSheetItem) {
            view.findViewById<TextView>(R.id.tv_contents).text = item.contents
        }
    }
}

data class BottomSheetItem(var contents: String)