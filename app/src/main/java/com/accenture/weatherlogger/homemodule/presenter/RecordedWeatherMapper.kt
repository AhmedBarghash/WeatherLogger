package com.accenture.weatherlogger.homemodule.presenter

import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto
import com.accenture.weatherlogger.homemodule.datalayer.apimanager.model.CurrentGeographicCoordinatesWeatherResponse

class RecordedWeatherMapper() {
    fun createRecordedWeatherDto(
        response: CurrentGeographicCoordinatesWeatherResponse,
        requestTime: String): RecordedWeatherDto {
        return RecordedWeatherDto(
            response.weather[0].description,
            response.weather[0].icon,
            response.main.temp,
            response.name,
            response.sys.sunrise,
            response.sys.sunset,
            response.wind.speed,
            response.main.pressure,
            response.main.humidity,
            response.main.feels_like,
            response.main.temp_min,
            response.main.temp_max,
            response.dt,
            requestTime,
            response.timezone
        )
    }
}