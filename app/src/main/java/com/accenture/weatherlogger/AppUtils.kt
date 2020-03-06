package com.accenture.weatherlogger

import java.util.*
import android.content.Context
import android.widget.ImageView
import java.text.SimpleDateFormat
import me.kartikarora.potato.Potato
import com.accenture.weatherlogger.homemodule.datalayer.database.AppDatabase
import com.squareup.picasso.Picasso

fun ImageView.loadByPicasso(url: String){
    Picasso.get()
        .load(url).into(this);
}

fun isNetworkAvailable(context: Context): Boolean {
    return Potato.potate(context).Utils().isInternetConnected
}

fun getDateFormat(dateInLong: Long): String {
    val date = Date(dateInLong)
    val sdf2 = SimpleDateFormat("EEEE", Locale.getDefault())
    return sdf2.format(date)
}

fun getTimeFormat(timeInLong: Long): String {
    val date = Date(timeInLong * 1000)
    val sdf2 = SimpleDateFormat("hh:mm aa", Locale.getDefault())
    return sdf2.format(date)
}

fun getCurrentTime() = SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(Date())!!

fun getDateTime() = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date()).toInt()

// Kelvin to Celsius
fun getTemperatureInCelsius(temperature: Double) = (temperature - 273.15).toInt().toString()

fun getDataBaseObject(context: Context) = AppDatabase(context)
