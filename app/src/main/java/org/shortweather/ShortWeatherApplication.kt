package org.shortweather

import android.app.Application
import android.content.SharedPreferences
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import org.shortweather.util.ShortWeatherSharedPreference


@HiltAndroidApp
class ShortWeatherApplication : Application(){
    override fun onCreate() {
        super.onCreate()
            ShortWeatherSharedPreference.setToken(this)
    }
}