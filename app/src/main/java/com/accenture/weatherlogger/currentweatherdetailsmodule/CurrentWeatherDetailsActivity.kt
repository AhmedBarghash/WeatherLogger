package com.accenture.weatherlogger.currentweatherdetailsmodule

import android.os.Bundle
import com.accenture.weatherlogger.*
import com.accenture.weatherlogger.bases.BaseActivity
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto
import kotlinx.android.synthetic.main.activity_current_weather_details.*

class CurrentWeatherDetailsActivity : BaseActivity() {

    private lateinit var recordedWeather: RecordedWeatherDto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_weather_details)
        recordedWeather = intent.getParcelableExtra("RecordedWeatherData")
        tv_country_name.text = recordedWeather.country
        tv_date.text = "${getDateFormat(recordedWeather.dt.toLong())} ${recordedWeather.currentTime}"
        iv_weather.loadByPicasso("${Constants.imageURL}${recordedWeather.icon}.png")
        tv_weather_value.text = "${getTemperatureInCelsius(recordedWeather.temp)}${"\u00B0"}C"
        tv_weather_description.text = recordedWeather.description
        tv_sunrise_value.text = getTimeFormat(recordedWeather.sunrise.toLong())
        tv_sunset_value.text = getTimeFormat(recordedWeather.sunset.toLong())
        tv_temp_max_value.text ="${getTemperatureInCelsius(recordedWeather.temp_max)}${"\u00B0"}C"
        tv_temp_min_value.text ="${getTemperatureInCelsius(recordedWeather.temp_min)}${"\u00B0"}C"
        tv_wind_value.text = "${recordedWeather.speed}M/S"

    }
}
