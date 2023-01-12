package org.shortweather.util

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.messaging.FirebaseMessaging

object ShortWeatherSharedPreference {
    fun setAccessToken(context: Context, token: String) { // 액세스 토큰 설정
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("tokens", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("AccessToken", token)
        editor.apply()
    }

    fun getAccessToken(context: Context): String { // 저장된 액세스 토큰 반환
        val prefs: SharedPreferences =
            context.getSharedPreferences("tokens", Context.MODE_PRIVATE)
        return prefs.getString("AccessToken", "").toString()
    }

    fun setDeviceToken(context: Context) { // 디바이스 토큰 설정
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            if (!it.isNullOrEmpty()) {
                val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("tokens", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("DeviceToken", it)
                editor.apply()
            }
        }
    }

    fun getDeviceToken(context: Context): String { // 저장된 디바이스 토큰 반환
        val prefs: SharedPreferences =
            context.getSharedPreferences("tokens", Context.MODE_PRIVATE)
        return prefs.getString("DeviceToken", "").toString()
    }

}