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
fun TextView.setTimeText(timeText: String, isCurrent: Boolean) {
    val hour = timeText.substring(0 until 2).toInt()
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
@BindingAdapter("tempSetting")
fun TextView.setTempSetting(tempText: Int) {
    this.text = "$tempText°"
}

@BindingAdapter("dayImageSetting", "dayImageDay")
fun ImageView.setImageSetting(imageSettingText: String, imageSettingDay: Boolean) {
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
        //"눈날림" -> imgId = 8
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