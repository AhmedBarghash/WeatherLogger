package com.accenture.weatherlogger.homemodule.datalayer.database.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecordedWeatherDto(
    val description: String,
    val icon: String,
    val temp: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int,
    val speed: Double,
    val pressure: Int,
    val humidity: Int,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val dt: Int,
    val currentTime: String,
    val timezone: Int
): Parcelable
