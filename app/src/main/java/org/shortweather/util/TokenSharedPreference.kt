package org.shortweather.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object TokenSharedPreference {
    fun getToken(context: Context): String { // 저장된 디바이스 토큰 반환
        val prefs: SharedPreferences =
            context.getSharedPreferences("sFile1", Context.MODE_PRIVATE)
        Log.d("tag", prefs.getString("Token1", "").toString())
        return prefs.getString("Token1", "").toString()
    }
}