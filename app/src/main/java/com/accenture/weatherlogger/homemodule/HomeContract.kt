package com.accenture.weatherlogger.homemodule

import android.content.Context
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto
import com.accenture.weatherlogger.homemodule.datalayer.apimanager.model.CurrentGeographicCoordinatesWeatherResponse

interface HomeContract {
    interface View {
        fun showLoader()
        fun hideLoader()
        fun showError(code: Int)
        fun showAddingWeatherDataView()
        fun showMessage(message: String)
        fun updateViewWithNewSavedData()
        fun onRecordedWeatherSelected(item: RecordedWeatherDto)
        fun presentOffLineWeatherData(offLineWeatherDataList: ArrayList<RecordedWeatherDto>)
        fun notifyViewWithNewData(item: RecordedWeatherDto)
    }

    interface Presenter {
        fun onDestroy()
        fun getOfflineWeatherData(applicationContext: Context)
        fun getCurrentDayWeather(latitude: Double, longitude: Double, context: Context)
        fun addNewRecordedWeatherData(context: Context, item: RecordedWeatherDto)
        fun readyToShowDetailsScreen(
            item: RecordedWeatherDto,
            applicationContext: Context
        )
    }

    interface InteractorOutput {
        fun onFailedReceivedCurrentWeatherData(it: Throwable)
        fun deliverOfflineWeatherData(offLineWeatherDataList: ArrayList<RecordedWeatherDto>)
        fun onCurrentWeatherDataReceivedSuccessful(
            response: CurrentGeographicCoordinatesWeatherResponse,
            context: Context,
            request: String
        )

        fun onSaveDataLocallySucceed(saveStatus: Boolean)
    }

    interface Interactor {
        fun saveWeatherDataLocally(
            context: Context?, record: RecordedWeatherDto,
            callBack: InteractorOutput
        )

        fun fetchCurrentDayWeather(
            latitude: Double,
            longitude: Double,
            context: Context,
            callBack: InteractorOutput
        )

        fun fetchOfflineWeatherData(context: Context?, callBack: InteractorOutput)
    }
}