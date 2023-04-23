package com.rikChatterjee.weatherfeeds.data.repository

import com.rikChatterjee.weatherfeeds.data.mappers.toWeatherInfo
import com.rikChatterjee.weatherfeeds.data.remote.WeatherApi
import com.rikChatterjee.weatherfeeds.domain.repository.WeatherRepository
import com.rikChatterjee.weatherfeeds.domain.weather.WeatherInfo
import com.rikChatterjee.weatherfeeds.utils.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api : WeatherApi
) : WeatherRepository{
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
               data = api.getWeatherData(
                   lat = lat,
                   long = long
               ).toWeatherInfo()
            )
        }catch (e : Exception){
            e.printStackTrace()
            Resource.Error<WeatherInfo>(message = e.message ?: "An unknown error occured")
        }
    }
}