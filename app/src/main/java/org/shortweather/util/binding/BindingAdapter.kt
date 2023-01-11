package org.shortweather.util.binding

import android.annotation.SuppressLint
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

@SuppressLint("SetTextI18n")
@BindingAdapter("timeSetting", "isCurrent")
fun TextView.setTimeText(timeText: String?, isCurrent: Boolean) {
    val hour = timeText!!.substring(0 until 2).toInt()
    if (isCurrent) {
        this.text = "지금"
    } else {
        when (hour) {
            in 1..11 -> {
                this.text = "오전 " + hour.toString() + "시"
            }
            0 -> {
                this.text = "오전 12시"
            }
            12 -> {
                this.text = "오후 12시"
            }
            else -> {
                this.text = "오후 " + (hour - 12).toString() + "시"
            }
        }
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("goTimeSetting")
fun TextView.setTitleTimeText(timeText: String?) {
    val hour: Int? = timeText?.substring(0 until 2)?.toInt()
    if (hour != null) {
        when (hour) {
            in 1..11 -> {
                this.text = "오전 " + hour.toString() + "시"
            }
            0 -> {
                this.text = "오전 12시"
            }
            12 -> {
                this.text = "오후 12시"
            }
            else -> {
                this.text = "오후 " + (hour - 12).toString() + "시"
            }
        }
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("sunTimeSetting")
fun TextView.setTimeSetting(sunTimeText: String?) {
    if (sunTimeText != null) {
        val hour = sunTimeText.substring(0 until 2)
        val min = sunTimeText.substring(2 until 4)
        this.text = "$hour:$min"
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("tempSetting")
fun TextView.setTempSetting(tempText: Int) {
    this.text = "$tempText°"
}

@BindingAdapter("dayImageSetting", "dayImageDay")
fun ImageView.setImageSetting(imageSettingText: String?, imageSettingDay: Boolean) {
    when (imageSettingText) {
        "맑음" -> {
            if (imageSettingDay) {
                this.setImageResource(R.drawable.ic_clear_day)
            } else {
                this.setImageResource(R.drawable.ic_clear_night)
            }
        }
        "흐림" -> this.setImageResource(R.drawable.ic_lotcloud_day)
        "구름많음" -> {
            if (imageSettingDay) {
                this.setImageResource(R.drawable.ic_lotcloud_day)
            } else {
                this.setImageResource(R.drawable.ic_lotcloud_night)
            }
        }
        "소나기" -> this.setImageResource(R.drawable.ic_shower)
        "이슬비" -> this.setImageResource(R.drawable.ic_lightrain)
        "비 또는 눈" -> this.setImageResource(R.drawable.ic_rainsnow)
        "진눈깨비" -> this.setImageResource(R.drawable.ic_lightsnow)
        "눈날림" -> this.setImageResource(R.drawable.ic_snowdrifting)
        "눈" -> this.setImageResource(R.drawable.ic_snow)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("precipitationSetting")
fun TextView.setPrecipitationSetting(precipitationText: Int) {
    this.text = "$precipitationText%"
}

@BindingAdapter("precipitationImageSetting")
fun ImageView.setImageSetting(rainText: Int) {
    when (rainText) {
        0 -> this.setImageResource(R.drawable.ic_precipitation_1)
        10 -> this.setImageResource(R.drawable.ic_precipitation_1)
        20 -> this.setImageResource(R.drawable.ic_precipitation_2)
        30 -> this.setImageResource(R.drawable.ic_precipitation_3)
        40 -> this.setImageResource(R.drawable.ic_precipitation_4)
        50 -> this.setImageResource(R.drawable.ic_precipitation_5)
        60 -> this.setImageResource(R.drawable.ic_precipitation_6)
        70 -> this.setImageResource(R.drawable.ic_precipitation_7)
        80 -> this.setImageResource(R.drawable.ic_precipitation_8)
        90 -> this.setImageResource(R.drawable.ic_precipitation_9)
        100 -> this.setImageResource(R.drawable.ic_precipitation_10)
    }
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

@BindingAdapter("dustTextSetting")
fun TextView.setDustTextSetting(dustText: Int) {
    when (dustText) {
        1 -> this.text = "좋음"
        2 -> this.text = "보통"
        3 -> this.text = "나쁨"
        4 -> this.text = "심각"
    }
}

@BindingAdapter("dustImageSetting")
fun ImageView.setDustImageSetting(dustText: Int) {
    when (dustText) {
        1 -> this.setImageResource(R.drawable.ic_dust_good)
        2 -> this.setImageResource(R.drawable.ic_dust_normal)
        3 -> this.setImageResource(R.drawable.ic_dust_bad)
        4 -> this.setImageResource(R.drawable.ic_dust_worst)
    }
}
