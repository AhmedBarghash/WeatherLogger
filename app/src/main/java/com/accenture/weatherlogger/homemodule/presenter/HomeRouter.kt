package com.accenture.weatherlogger.homemodule.presenter

import android.content.Intent
import android.content.Context
import com.accenture.weatherlogger.R
import com.accenture.weatherlogger.currentweatherdetailsmodule.CurrentWeatherDetailsActivity
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto

class HomeRouter {

    fun navigateToCurrentWeatherDetailsScreen(
        item: RecordedWeatherDto,
        applicationContext: Context
    ) {
        val intent = Intent(applicationContext, CurrentWeatherDetailsActivity::class.java)
        intent.putExtra("RecordedWeatherData", item)
        applicationContext.startActivity(intent)
    }
}