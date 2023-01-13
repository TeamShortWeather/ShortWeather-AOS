package org.shortweather.util.extension

import android.view.View
import org.shortweather.util.OnThrottleClickListener

fun View.setOnThrottleClickListener(action: (v: View) -> Unit) {
    val listener = View.OnClickListener { action(it) }
    setOnClickListener(OnThrottleClickListener(listener))
}

// with interval setting
fun View.setOnThrottleClickListener(action: (v: View) -> Unit, interval: Long) {
    val listener = View.OnClickListener { action(it) }
    setOnClickListener(OnThrottleClickListener(listener, interval))
}