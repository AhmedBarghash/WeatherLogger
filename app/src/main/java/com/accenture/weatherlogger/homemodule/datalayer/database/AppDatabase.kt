package com.accenture.weatherlogger.homemodule.datalayer.database

import android.content.Context
import android.database.Cursor
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import android.database.sqlite.SQLiteOpenHelper
import com.accenture.weatherlogger.homemodule.datalayer.database.dao.RecordedWeatherDao
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto


open class AppDatabase(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "weather_db"
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int): Unit {
        db.execSQL("DROP TABLE IF EXISTS " + RecordedWeatherDao.TABLE_NAME)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(RecordedWeatherDao.CREATE_TABLE)
    }

    fun insertWeatherData(weatherData: RecordedWeatherDto): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(RecordedWeatherDao.CURRENT_DATA, weatherData.dt)
        values.put(RecordedWeatherDao.ICON, weatherData.icon)
        values.put(RecordedWeatherDao.DESCRIPTION, weatherData.description)
        values.put(RecordedWeatherDao.TEMP, weatherData.temp)
        values.put(RecordedWeatherDao.CITY, weatherData.country)
        values.put(RecordedWeatherDao.SUNRISE, weatherData.sunrise)
        values.put(RecordedWeatherDao.SUNSET, weatherData.sunset)
        values.put(RecordedWeatherDao.SPEED, weatherData.speed)
        values.put(RecordedWeatherDao.PRESSURE, weatherData.pressure)
        values.put(RecordedWeatherDao.HUMIDITY, weatherData.humidity)
        values.put(RecordedWeatherDao.FEELS_LIKE, weatherData.feels_like)
        values.put(RecordedWeatherDao.TEMP_MIN, weatherData.temp_min)
        values.put(RecordedWeatherDao.TEMP_MAX, weatherData.temp_max)
        values.put(RecordedWeatherDao.TIMEZONE, weatherData.timezone)
        values.put(RecordedWeatherDao.CURRENT_TIME, weatherData.currentTime)

        // this to replace the new data with the same current date(API date format).
        val id = db.insertWithOnConflict(RecordedWeatherDao.TABLE_NAME, null, values,CONFLICT_REPLACE)
        db.close()
        // NOTE if the value -1 that mean something went wrong of try to insert data with the same id
        return id
    }

    fun getAllRecordedWeather(): ArrayList<RecordedWeatherDto> {
        val recordedWeatherDataList = ArrayList<RecordedWeatherDto>()
        val selectQuery =
            "SELECT  * FROM " + RecordedWeatherDao.TABLE_NAME + " ORDER BY " +
                    RecordedWeatherDao.CURRENT_DATA + " DESC"
        val db = this.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                recordedWeatherDataList.add(
                    RecordedWeatherDto(
                        cursor.getString(cursor.getColumnIndex(RecordedWeatherDao.DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(RecordedWeatherDao.ICON)),
                        cursor.getDouble(cursor.getColumnIndex(RecordedWeatherDao.TEMP)),
                        cursor.getString(cursor.getColumnIndex(RecordedWeatherDao.CITY)),
                        cursor.getInt(cursor.getColumnIndex(RecordedWeatherDao.SUNRISE)),
                        cursor.getInt(cursor.getColumnIndex(RecordedWeatherDao.SUNSET)),
                        cursor.getDouble(cursor.getColumnIndex(RecordedWeatherDao.SPEED)),
                        cursor.getInt(cursor.getColumnIndex(RecordedWeatherDao.PRESSURE)),
                        cursor.getInt(cursor.getColumnIndex(RecordedWeatherDao.HUMIDITY)),
                        cursor.getDouble(cursor.getColumnIndex(RecordedWeatherDao.FEELS_LIKE)),
                        cursor.getDouble(cursor.getColumnIndex(RecordedWeatherDao.TEMP_MIN)),
                        cursor.getDouble(cursor.getColumnIndex(RecordedWeatherDao.TEMP_MAX)),
                        cursor.getInt(cursor.getColumnIndex(RecordedWeatherDao.CURRENT_DATA)),
                        cursor.getString(cursor.getColumnIndex(RecordedWeatherDao.CURRENT_TIME)),
                        cursor.getInt(cursor.getColumnIndex(RecordedWeatherDao.TIMEZONE))
                    )
                )
            } while (cursor.moveToNext())
        }
        db.close()
        return recordedWeatherDataList
    }


}