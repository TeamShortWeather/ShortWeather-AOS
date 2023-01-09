package org.shortweather

import android.app.Application
import android.content.SharedPreferences
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ShortWeatherApplication : Application(){
    override fun onCreate() {
        super.onCreate()
            FirebaseMessaging.getInstance().token.addOnSuccessListener {
            if(!it.isNullOrEmpty()){
                val sharedPreferences = getSharedPreferences("sFile1", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                val token = it // 사용자가 입력한 저장할 데이터
                editor.putString("Token1", token) // key, value를 이용하여 저장하는 형태
                editor.commit()
            }
        }
    }
}