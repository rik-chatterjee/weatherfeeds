package com.rikChatterjee.weatherfeeds.data.remote

import com.rikChatterjee.weatherfeeds.utils.Constants.BASE_URL
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(BASE_URL)
    suspend fun getWeatherData(
        @Query("latitude") lat : Double,
        @Query("longitude") long : Double
    ) : WeatherDto
}