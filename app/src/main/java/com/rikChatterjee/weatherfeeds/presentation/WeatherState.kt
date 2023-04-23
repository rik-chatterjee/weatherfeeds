package com.rikChatterjee.weatherfeeds.presentation

import com.rikChatterjee.weatherfeeds.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo : WeatherInfo? = null,
    val isLoading :  Boolean =false,
    val error : String? = null
)