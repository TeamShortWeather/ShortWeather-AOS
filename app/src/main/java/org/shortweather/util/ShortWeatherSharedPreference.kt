package org.shortweather.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

object ShortWeatherSharedPreference {
    fun setAccessToken(context: Context, token: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("sFile1", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val accessToken = token // 사용자가 입력한 저장할 데이터
        editor.putString("AccessToken1", token) // key, value를 이용하여 저장하는 형태
        editor.commit()
    }

    fun getAccessToken(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences("sFile1", Context.MODE_PRIVATE)
        return prefs.getString("AccessToken1", "").toString()
    }

    fun getToken(context: Context): String { // 저장된 디바이스 토큰 반환
        val prefs: SharedPreferences =
            context.getSharedPreferences("sFile1", Context.MODE_PRIVATE)
        Log.d("tag", prefs.getString("Token1", "").toString())
        return prefs.getString("Token1", "").toString()
    }

    fun setToken(context: Context) {
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            if (!it.isNullOrEmpty()) {
                val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("sFile1", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                val token = it // 사용자가 입력한 저장할 데이터
                editor.putString("Token1", token) // key, value를 이용하여 저장하는 형태
                editor.commit()
            }
        }
    }
}