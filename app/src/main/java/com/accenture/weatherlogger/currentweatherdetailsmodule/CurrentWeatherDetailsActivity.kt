package com.accenture.weatherlogger.currentweatherdetailsmodule

import android.os.Bundle
import com.accenture.weatherlogger.R
import com.accenture.weatherlogger.bases.BaseActivity
import com.accenture.weatherlogger.homemodule.datalayer.database.dao.RecordedWeatherDao

class CurrentWeatherDetailsActivity : BaseActivity() {

    private lateinit var recordedWeather: RecordedWeatherDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_weather_details)
        val recordedWeather: RecordedWeatherDao = intent.getParcelableExtra<RecordedWeatherDao>("RecordedWeatherData") as RecordedWeatherDao
    }
}
