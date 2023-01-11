package org.shortweather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.shortweather.data.repository.AuthRepositoryImpl
import org.shortweather.data.repository.CustomWeatherRepositoryImpl
import org.shortweather.data.repository.SampleRepositoryImpl
import org.shortweather.domain.repository.AuthRepository
import org.shortweather.domain.repository.CustomWeatherRepository
import org.shortweather.domain.repository.SampleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsSampleRepository(sampleRepositoryImpl: SampleRepositoryImpl): SampleRepository

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsCustomWeatherRepository(customWeatherRepositoryImpl: CustomWeatherRepositoryImpl): CustomWeatherRepository

}