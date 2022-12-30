package org.shortweather.util.extension

import android.view.View
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}