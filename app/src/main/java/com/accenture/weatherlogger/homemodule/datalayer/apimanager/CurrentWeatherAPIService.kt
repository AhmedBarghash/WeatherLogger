package com.accenture.weatherlogger.homemodule.datalayer.apimanager

import CurrentGeographicCoordinatesWeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherAPIService {
    @GET("weather")
    fun getCurrentWeather(@Query("lat") lat: Double,@Query("lon") lon: Double,@Query("appid") appid: String)
            : Observable<CurrentGeographicCoordinatesWeatherResponse>

}