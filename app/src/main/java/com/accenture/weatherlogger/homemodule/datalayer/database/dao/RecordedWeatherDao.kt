package com.accenture.weatherlogger.homemodule.datalayer.database.dao


class RecordedWeatherDao {

    companion object {
        const val TABLE_NAME = "recorded_weather"
        const val DESCRIPTION = "description"
        const val ICON = "icon"
        const val TEMP = "temp"
        const val CITY = "city"
        const val SUNRISE = "sunrise"
        const val SUNSET = "sunset"
        const val SPEED = "speed"
        const val PRESSURE = "pressure"
        const val HUMIDITY = "humidity"
        const val FEELS_LIKE = "feels_like"
        const val TEMP_MIN = "temp_min"
        const val TEMP_MAX = "temp_max"
        const val CURRENT_DATA = "currentData"
        const val CURRENT_TIME = "time"
        const val TIMEZONE = "timezone"

        // Create table SQL query
        const val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + CURRENT_DATA + " INTEGER PRIMARY KEY,"
                + CURRENT_TIME + " TEXT,"
                + ICON + " TEXT,"
                + DESCRIPTION + " TEXT,"
                + TEMP + " REAL,"
                + CITY + " TEXT,"
                + SUNRISE + " REAL,"
                + SUNSET + " REAL,"
                + SPEED + " INTEGER,"
                + PRESSURE + " INTEGER,"
                + HUMIDITY + " INTEGER,"
                + FEELS_LIKE + " REAL,"
                + TEMP_MIN + " REAL,"
                + TEMP_MAX + " REAL,"
                + TIMEZONE + " REAL"
                + ")")
    }
}