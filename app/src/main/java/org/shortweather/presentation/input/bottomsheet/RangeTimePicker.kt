/*
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.widget.NumberPicker
import android.widget.TimePicker
import androidx.core.math.MathUtils

class RangeTimePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TimePicker(context, attrs) {
    private val defaultInterval = 30
    val minInterval = 1
    val maxInterval = 30

    var timeInterval = defaultInterval
        set(value) {
            if (field !in minInterval..maxInterval) {
                Log.w("RangeTimePicker", "timeInterval must be between $minInterval..$maxInterval")
            }

            field = MathUtils.clamp(minInterval, maxInterval, value)
            setInterval(field)
            invalidate()
        }

    init {
        setInterval()
    }

    @SuppressLint("PrivateApi")
    fun setInterval(
        @androidx.annotation.IntRange(from = 1, to = 30)
        timeInterval: Int = defaultInterval
    ) {
        try {
            (this.findViewById(
                Resources.getSystem().getIdentifier(
                    "minute",
                    "id",
                    "android"
                )
            ) as NumberPicker).apply {
                minValue = 0
                maxValue = 60 / timeInterval - 1
                displayedValues = getDisplayedValue()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getDisplayedMinutes(): Int = minute * timeInterval

    fun getDisplayedValue(
        interval: Int = timeInterval
    ): Array<String> {
        val minutesArray = ArrayList<String>()
        for (i in 0 until 60 step interval) {
            minutesArray.add(String.format(i.toString()))
        }

        return minutesArray.toArray(arrayOf(""))
    }
}
*/
