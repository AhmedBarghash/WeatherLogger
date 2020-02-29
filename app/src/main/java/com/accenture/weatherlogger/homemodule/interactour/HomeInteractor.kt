package com.accenture.weatherlogger.homemodule.interactour

import android.util.Log
import com.accenture.weatherlogger.bases.APIClient
import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.datalayer.apimanager.CurrentWeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class HomeInteractor : HomeContract.Interactor {
    private var retrofit: CurrentWeatherAPIService? = null

    override fun fetchComingWeekWeather(
        latitude: Double,
        longitude: Double,
        appid: String,
        callBack: HomeContract.InteractorOutput
    ) {
    // TODO check if the retrofit == null
        if (retrofit != null)
            retrofit= APIClient.getClient()?.create(CurrentWeatherAPIService::class.java)

            retrofit!!.getCurrentWeather(latitude,longitude,appid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    callBack.onQueryGettingComingWeekWeatherSucceed(it)
                },{
                    // create a error response
                    val error = (it as HttpException).code()
                    callBack.onQueryGettingComingWeekWeatherFailed(error)
                })

    }

    override fun fetchCurrentDayWeather(latitude: Double, longitude: Double, appid: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}