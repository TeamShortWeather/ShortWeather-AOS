package org.shortweather.util

import com.google.firebase.messaging.FirebaseMessagingService

class ShortWeatherFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}