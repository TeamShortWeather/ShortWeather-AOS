/*
package org.shortweather.util.extension

import android.annotation.SuppressLint
import android.util.Log
import android.widget.NumberPicker
import android.widget.TimePicker
import java.lang.reflect.Field

const val DEFAULT_INTERVAL = 30
const val MINUTES_MIN = 0
const val MINUTES_MAX = 60

@SuppressLint("PrivateApi")
fun TimePicker.setTimeInterval(
    @androidx.annotation.IntRange(from = 0, to = 60)
    timeInterval: Int = DEFAULT_INTERVAL
) {
    try {
        val classForid = Class.forName("com.android.internal.R\$id")
        val timePickerField: Field = classForid.getField("timePicker")
        val mTimePicker = findViewById<TimePicker>(timePickerField.getInt(null))
        val field: Field = classForid.getField("minute")
        val minuteSpinner = mTimePicker!!
            .findViewById(field.getInt(null)) as NumberPicker
        minuteSpinner.minValue = 0
        minuteSpinner.maxValue = 60 / timeInterval - 1
        val displayedValues: MutableList<String> = ArrayList()
        var i = 0
        while (i < 60) {
            displayedValues.add(String.format("%02d", i))
            i += timeInterval
        }
        minuteSpinner.displayedValues = displayedValues
            .toTypedArray()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun getDisplayedValue(
    @androidx.annotation.IntRange(from = 0, to = 60)
    timeInterval: Int = DEFAULT_INTERVAL
): Array<String> {
    val minutesArray = ArrayList<String>()
    for (i in 0 until MINUTES_MAX step timeInterval) {
        minutesArray.add(i.toString())
    }

    return minutesArray.toArray(arrayOf(""))
}

fun TimePicker.getDisplayedMinute(
    @androidx.annotation.IntRange(from = 0, to = 60)
    timeInterval: Int = DEFAULT_INTERVAL
): Int = minute * timeInterval
*/
