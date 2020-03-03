package com.accenture.weatherlogger.currentweatherdetailsmodule

import android.os.Bundle
import android.util.Log
import com.accenture.weatherlogger.R
import com.accenture.weatherlogger.bases.BaseActivity

class CurrentWeatherDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_weather_details)
    }
}
