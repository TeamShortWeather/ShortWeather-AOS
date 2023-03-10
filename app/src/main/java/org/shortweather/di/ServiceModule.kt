package org.shortweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.shortweather.data.api.AuthService
import org.shortweather.data.api.CustomWeatherService
import org.shortweather.data.api.TodayWeatherService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesTodayWeatherService(retrofit: Retrofit): TodayWeatherService =
        retrofit.create(TodayWeatherService::class.java)

    @Provides
    @Singleton
    fun providesCustomWeatherService(retrofit: Retrofit): CustomWeatherService =
        retrofit.create(CustomWeatherService::class.java)
}