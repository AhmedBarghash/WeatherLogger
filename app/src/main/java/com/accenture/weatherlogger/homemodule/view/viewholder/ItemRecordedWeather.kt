package com.accenture.weatherlogger.homemodule.view.viewholder

import android.view.View
import com.accenture.weatherlogger.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import com.accenture.weatherlogger.homemodule.HomeContract
import kotlinx.android.synthetic.main.item_recorded_weather.view.*
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto

class ItemRecordedWeather(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun onBindView(item: RecordedWeatherDto, callback: HomeContract.View) {
        containerView.tv_country_name.text = item.country
        containerView.tv_date.text = "${getDateFormat(item.dt.toLong())} ${item.currentTime}"
        containerView.tv_sunrise_value.text = getTimeFormat(item.sunrise.toLong())
        containerView.tv_sunset_value.text = getTimeFormat(item.sunset.toLong())
        containerView.tv_weather_value.text = String.format(getTemperatureInCelsius(item.temp), "C")
        containerView.iv_weather.loadByGlide("${Constants.imageURL}${item.icon}.png")
        containerView.setOnClickListener {
            callback.onRecordedWeatherSelected(item)
        }
    }
}