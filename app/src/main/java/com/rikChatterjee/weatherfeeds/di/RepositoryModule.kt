package com.rikChatterjee.weatherfeeds.di

import com.rikChatterjee.weatherfeeds.data.location.DefaultLocationTracker
import com.rikChatterjee.weatherfeeds.data.repository.WeatherRepositoryImpl
import com.rikChatterjee.weatherfeeds.domain.location.LocationTracker
import com.rikChatterjee.weatherfeeds.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl : WeatherRepositoryImpl
    ) : WeatherRepository
}