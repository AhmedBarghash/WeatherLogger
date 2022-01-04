package com.accenture.weatherlogger.homemodule.presenter

import retrofit2.HttpException
import android.content.Context
import kotlin.collections.ArrayList
import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto
import com.accenture.weatherlogger.homemodule.interactour.HomeInteractor
import com.accenture.weatherlogger.homemodule.datalayer.apimanager.model.CurrentGeographicCoordinatesWeatherResponse
import java.net.UnknownHostException

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

    override fun onFailedReceivedCurrentWeatherData(it: Throwable) {
        view?.hideLoader()
        try {
            it.message?.let { message -> if(message.contains("401"))  view?.showError(401)else  view?.showError(404)}
        } catch (exception: HttpException) {
            val code = exception.code()
            view?.showError(code)
        } catch (exception: UnknownHostException) {
            view?.showError(404)
        }
    }

    override fun getOfflineWeatherData(applicationContext: Context) {
        view?.showLoader()
        interactor?.fetchOfflineWeatherData(applicationContext, this)
    }

    override fun deliverOfflineWeatherData(offLineWeatherDataList: ArrayList<RecordedWeatherDto>) {
        view?.hideLoader()
        if (offLineWeatherDataList.isEmpty()) {
            view?.showAddingWeatherDataView(true)
            return
        }
        view?.presentOffLineWeatherData(offLineWeatherDataList)
    }

    override fun onRequestTimeout() {
        view?.hideLoader()
        view?.showError(101)

    }

    override fun readyToShowDetailsScreen(
        item: RecordedWeatherDto,
        applicationContext: Context
    ) {
        router = HomeRouter()
        router.navigateToCurrentWeatherDetailsScreen(item, applicationContext)
    }

    override fun onDestroy() {
        interactor = null
        view = null
    }
}