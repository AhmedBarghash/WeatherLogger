package com.accenture.weatherlogger.homemodule.datalayer.apimanager

import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import com.accenture.weatherlogger.homemodule.datalayer.apimanager.model.CurrentGeographicCoordinatesWeatherResponse

interface CurrentWeatherAPIService {
    @GET("weather")
    fun getCurrentWeather(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") appid: String)
            : Observable<CurrentGeographicCoordinatesWeatherResponse>
}