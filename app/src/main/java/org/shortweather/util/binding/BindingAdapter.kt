package org.shortweather.util.binding

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import org.shortweather.R

@BindingAdapter("visibility")
fun View.setVisibility(isVisible: Boolean?) {
    if (isVisible == null) return
    this.isVisible = isVisible
}

@SuppressLint("SetTextI18n")
@BindingAdapter("timeSetting", "isCurrent")
fun TextView.setTimeText(timeText: String?, isCurrent: Boolean) {
    if (timeText == null) return
    val hour = timeText.substring(0 until 2).toInt()
    if (isCurrent) {
        this.text = "지금"
    } else {
        when (hour) {
            in 1..11 -> this.text = "오전 " + hour.toString() + "시"
            0 -> this.text = "오전 12시"
            12 -> this.text = "오후 12시"
            else -> this.text = "오후 " + (hour - 12).toString() + "시"
        }
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("goTimeSetting")
fun TextView.setTitleTimeText(timeText: String?) {
    if (timeText == null) return
    when (val hour = timeText.substring(0 until 2).toInt()) {
        in 1..11 -> this.text = "오전 " + hour.toString() + "시"
        0 -> this.text = "오전 12시"
        12 -> this.text = "오후 12시"
        else -> this.text = "오후 " + (hour - 12).toString() + "시"
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("sunTimeSetting")
fun TextView.setTimeSetting(sunTimeText: String?) {
    if (sunTimeText == null) return
    val hour = sunTimeText.substring(0 until 2).toInt()
    val min = sunTimeText.substring(2 until 4)
    if (hour > 12) {
        this.text = (hour - 12).toString() + ":" + min
    } else {
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
    if (imageSettingText == null) return
    when (imageSettingText) {
        "맑음" -> {
            if (imageSettingDay) {
                this.setImageResource(R.drawable.ic_clear_day)
            } else {
                this.setImageResource(R.drawable.ic_clear_night)
            }
        }
        "흐림" -> this.setImageResource(R.drawable.ic_cloudy)
        "구름많음" -> {
            if (imageSettingDay) {
                this.setImageResource(R.drawable.ic_lotcloud_day)
            } else {
                this.setImageResource(R.drawable.ic_lotcloud_night)
            }
        }
        "소나기" -> this.setImageResource(R.drawable.ic_shower)
        "이슬비" -> this.setImageResource(R.drawable.ic_lightrain)
        "비" -> this.setImageResource(R.drawable.ic_rain)
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
        in 0..9 -> this.setImageResource(R.drawable.ic_precipitation_1)
        in 10..19 -> this.setImageResource(R.drawable.ic_precipitation_2)
        in 20..29 -> this.setImageResource(R.drawable.ic_precipitation_3)
        in 30..39 -> this.setImageResource(R.drawable.ic_precipitation_4)
        in 40..49 -> this.setImageResource(R.drawable.ic_precipitation_5)
        in 50..59 -> this.setImageResource(R.drawable.ic_precipitation_6)
        in 60..69 -> this.setImageResource(R.drawable.ic_precipitation_7)
        in 70..79 -> this.setImageResource(R.drawable.ic_precipitation_8)
        in 80..89 -> this.setImageResource(R.drawable.ic_precipitation_9)
        in 90..100 -> this.setImageResource(R.drawable.ic_precipitation_10)
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
    if (type == null) return
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
    }
}

@BindingAdapter("weatherBackgroundType", "isDay")
fun ImageView.setWeatherBackground(type: String?, isDay: Boolean) {
    if (type == null) return
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

@SuppressLint("SetTextI18n")
@BindingAdapter("weeklyRainSetting")
fun TextView.setWeeklyRainSetting(weeklyRainText: Int) {
    if (weeklyRainText == 0) {
        this.text = " "
    } else {
        this.text = "$weeklyRainText%"
    }
}

@BindingAdapter("textColorSetting")
fun TextView.setTextColorSetting(weeklyDayText: String) {
    if (weeklyDayText != "오늘") {
        this.setTextColor(context.getColor(R.color.short_weather_gray_4))
    } else {
        this.setTextColor(context.getColor(R.color.short_weather_black))
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("weeklyDateSetting")
fun TextView.setWeeklyDateSetting(weeklyDateText: String?) {
    if (weeklyDateText == null) return
    val month = weeklyDateText.substring(0 until 2).toInt()
    val day = weeklyDateText.substring(2 until 4)
    this.text = "$month.$day"
}

@BindingAdapter("dayTextColorSetting")
fun TextView.setDayColorSetting(weeklyDayText: String) {
    if (weeklyDayText == "일" || weeklyDayText == "내일") {
        this.text = weeklyDayText
        this.setTextColor(context.getColor(R.color.short_weather_weekend))
    } else {
        this.text = weeklyDayText
        this.setTextColor(context.getColor(R.color.short_weather_black))
    }
}