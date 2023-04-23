package com.rikChatterjee.weatherfeeds.domain.repository

import com.rikChatterjee.weatherfeeds.domain.weather.WeatherInfo
import com.rikChatterjee.weatherfeeds.utils.Resource

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}