package org.shortweather.util.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import org.shortweather.R

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String) {
    this.load(imageUrl)
}

@BindingAdapter("visibility")
fun View.setVisibility(isVisible: Boolean?) {
    if (isVisible == null) return
    this.isVisible = isVisible
}

@BindingAdapter("subTitle")
fun TextView.setSubTitle(temperature: Int) {
    this.text = "어제보다 $temperature°"
}

@BindingAdapter("temperature")
fun TextView.setTemperature(temperature: Int) {
    this.text = "$temperature°"
}

@BindingAdapter("toast")
fun TextView.setToast(temperature: Int) {
    this.text = "어제는 $temperature°로"
}

@BindingAdapter("dustBackground")
fun TextView.setDustBackground(level: Int) {
    when (level) {
        1 -> this.setBackgroundResource(R.drawable.bg_good_dust)
        2 -> this.setBackgroundResource(R.drawable.bg_nomal_dust)
        3 -> this.setBackgroundResource(R.drawable.bg_bad_dust)
        4 -> this.setBackgroundResource(R.drawable.bg_worst_dust)
    }
}

@BindingAdapter("dustTextColor")
fun TextView.setDustTextColor(level: Int) {
    when (level) {
        1 -> this.setTextColor(context.getColor(R.color.short_weather_dustgood2))
        2 -> this.setTextColor(context.getColor(R.color.short_weather_dustnormal2))
        3 -> this.setTextColor(context.getColor(R.color.short_weather_dustbad2))
        4 -> this.setTextColor(context.getColor(R.color.short_weather_dustworst2))
    }
}