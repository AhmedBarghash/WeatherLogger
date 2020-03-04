package com.accenture.weatherlogger.homemodule.presenter

import android.util.Log
import retrofit2.HttpException
import android.content.Context
import kotlin.collections.ArrayList
import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto
import com.accenture.weatherlogger.homemodule.interactour.HomeInteractor
import com.accenture.weatherlogger.homemodule.datalayer.apimanager.model.CurrentGeographicCoordinatesWeatherResponse

class HomePresenter(private var view: HomeContract.View?) : HomeContract.Presenter,
    HomeContract.InteractorOutput {

    private var interactor: HomeContract.Interactor? = HomeInteractor()
    private lateinit var router: HomeRouter
    override fun getCurrentDayWeather(latitude: Double, longitude: Double, context: Context) {
        view?.showLoader()
        interactor?.fetchCurrentDayWeather(latitude, longitude, context, this)
    }

    override fun onCurrentWeatherDataReceivedSuccessful(
        response: CurrentGeographicCoordinatesWeatherResponse, context: Context, requestTime: String) {
        val recordedWeatherObject = RecordedWeatherMapper().createRecordedWeatherDto(response,requestTime)
        view?.notifyViewWithNewData(recordedWeatherObject)
    }

    override fun addNewRecordedWeatherData(context: Context, item: RecordedWeatherDto) {
        interactor?.saveWeatherDataLocally(context, item, this)
    }

    override fun onSaveDataLocallySucceed(savedState: Boolean) {
        view?.hideLoader()
        if (savedState) {
            view?.showError(101)
            return
        }
        view?.updateViewWithNewSavedData()
    }

    override fun onFailedReceivedCurrentWeatherData(throwable: Throwable) {
        view?.hideLoader()
        view?.showError((throwable as HttpException).code())
    }

    override fun getOfflineWeatherData(applicationContext: Context) {
        view?.showLoader()
        interactor?.fetchOfflineWeatherData(applicationContext, this)
    }

    override fun deliverOfflineWeatherData(offLineWeatherDataList: ArrayList<RecordedWeatherDto>) {
        view?.hideLoader()
        if (offLineWeatherDataList.isEmpty()) {
            view?.showAddingWeatherDataView()
            return
        }
        view?.presentOffLineWeatherData(offLineWeatherDataList)
        for (item in offLineWeatherDataList) {
            Log.i("Hello", item.toString())
        }
    }

    override fun readyToShowDetailsScreen(
        item: RecordedWeatherDto,
        applicationContext: Context
    ) {
        router = HomeRouter()
        router.navigateToCurrentWeatherDetailsScreen(item,applicationContext)
    }

    override fun onDestroy() {
        interactor = null
        view = null
    }
}