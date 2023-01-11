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
    if (temperature <= 0) {
        this.text = "어제보다 $temperature°"
    } else {
        this.text = "어제보다 +$temperature°"
    }
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

@BindingAdapter("weatherImageType", "isDay")
fun ImageView.setWeatherImage(type: String?, isDay: Boolean) {
    when (type) {
        "맑음" -> {
            if (isDay) {
                this.setImageResource(R.drawable.img_clear_day)
            } else {
                this.setImageResource(R.drawable.img_clear_night)
            }
        }
        "구름많음" -> {
            if (isDay) {
                this.setImageResource(R.drawable.img_lotcloud_day)
            } else {
                this.setImageResource(R.drawable.img_lotcloud_night)
            }
        }
        "흐림" -> this.setImageResource(R.drawable.img_cloudy)
        "소나기" -> this.setImageResource(R.drawable.img_shower)
        "이슬비" -> this.setImageResource(R.drawable.img_lightrain)
        "비" -> this.setImageResource(R.drawable.img_rain)
        "비 또는 눈" -> this.setImageResource(R.drawable.img_rainsnow)
        "진눈깨비" -> this.setImageResource(R.drawable.img_lightsnow)
        "눈날림" -> this.setImageResource(R.drawable.img_snowdrifting)
        "눈" -> this.setImageResource(R.drawable.img_snow)
        null -> {
            if (isDay) {
                this.setImageResource(R.drawable.img_clear_day)
            } else {
                this.setImageResource(R.drawable.img_clear_night)
            }
        }
        else -> throw IllegalArgumentException("not found.")
    }
}

@BindingAdapter("weatherBackgroundType", "isDay")
fun ImageView.setWeatherBackground(type: String?, isDay: Boolean) {
    when (type) {
        "맑음" -> {
            if (isDay) {
                this.setBackgroundResource(R.drawable.bg_day)
            } else {
                this.setBackgroundResource(R.drawable.bg_night)
            }
        }
        "구름많음" -> {
            if (isDay) {
                this.setBackgroundResource(R.drawable.bg_day)
            } else {
                this.setBackgroundResource(R.drawable.bg_night)
            }
        }
        "흐림" -> this.setBackgroundResource(R.drawable.bg_cloud)
        "소나기" -> this.setBackgroundResource(R.drawable.bg_cloud)
        "이슬비" -> this.setBackgroundResource(R.drawable.bg_rain)
        "비" -> this.setBackgroundResource(R.drawable.bg_rain)
        "비 또는 눈" -> this.setBackgroundResource(R.drawable.bg_snow)
        "진눈깨비" -> this.setBackgroundResource(R.drawable.bg_snow)
        "눈날림" -> this.setBackgroundResource(R.drawable.bg_snow)
        "눈" -> this.setBackgroundResource(R.drawable.bg_snow)
        null -> {
            if (isDay) {
                this.setBackgroundResource(R.drawable.bg_day)
            } else {
                this.setBackgroundResource(R.drawable.bg_night)
            }
        }
        else -> throw IllegalArgumentException("not found.")
    }
}