package com.accenture.weatherlogger.homemodule.interactour

import android.util.Log
import android.content.Context
import io.reactivex.schedulers.Schedulers
import com.accenture.weatherlogger.Constants
import com.accenture.weatherlogger.bases.APIClient
import com.accenture.weatherlogger.getCurrentTime
import com.accenture.weatherlogger.getDataBaseObject
import io.reactivex.android.schedulers.AndroidSchedulers
import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto
import com.accenture.weatherlogger.homemodule.datalayer.apimanager.CurrentWeatherAPIService

class HomeInteractor() : HomeContract.Interactor {

    private var retrofit: CurrentWeatherAPIService? = null

    override fun fetchCurrentDayWeather(
        latitude: Double, longitude: Double, context: Context,
        callBack: HomeContract.InteractorOutput
    ) {
        if (retrofit == null) {
            retrofit = APIClient.getClient()?.create(CurrentWeatherAPIService::class.java)
        }
        val requestTime = getCurrentTime()
        retrofit!!.getCurrentWeather(latitude, longitude ,Constants.appIdKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                callBack.onCurrentWeatherDataReceivedSuccessful(it, context,requestTime)
            }, {
                callBack.onFailedReceivedCurrentWeatherData(it)
            })

    }

    override fun saveWeatherDataLocally(
        context: Context?, record: RecordedWeatherDto,
        callBack: HomeContract.InteractorOutput
    ) {
        if (context == null) {
            Log.i("Error ${this.javaClass.name}", "context is = null")
            return
        }
        callBack.onSaveDataLocallySucceed((getDataBaseObject(context).insertWeatherData(record)).toInt() == -1)
    }

    override fun fetchOfflineWeatherData(
        context: Context?,
        callBack: HomeContract.InteractorOutput
    ) {
        if (context == null) {
            Log.i("Error ${this.javaClass.name}", "context is = null")
            return
        }
        callBack.deliverOfflineWeatherData(getDataBaseObject(context).getAllRecordedWeather())
    }
}