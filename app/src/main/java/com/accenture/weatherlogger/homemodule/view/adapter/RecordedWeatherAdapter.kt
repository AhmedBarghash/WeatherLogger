package com.accenture.weatherlogger.homemodule.view.adapter

import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import com.accenture.weatherlogger.R
import androidx.recyclerview.widget.RecyclerView
import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto
import com.accenture.weatherlogger.homemodule.view.viewholder.ItemRecordedWeather

class RecordedWeatherAdapter(
    private var mContext: Context,
    private var recordedWeatherList: ArrayList<RecordedWeatherDto>,
    private var callback: HomeContract.View
) : RecyclerView.Adapter<ItemRecordedWeather>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecordedWeather {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.item_recorded_weather, parent, false)
        return ItemRecordedWeather(view)
    }

    override fun getItemCount() = recordedWeatherList.size

    override fun onBindViewHolder(holder: ItemRecordedWeather, position: Int) {
        holder.onBindView(recordedWeatherList[position], callback)
    }
}