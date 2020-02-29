package com.accenture.weatherlogger.homemodule.presenter

import CurrentGeographicCoordinatesWeatherResponse
import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.interactour.HomeInteractor

class HomePresenter(private var view: HomeContract.View?) : HomeContract.Presenter,
    HomeContract.InteractorOutput {

    private var interactor: HomeContract.Interactor? = HomeInteractor()

    override fun onDestroy() {
        interactor = null
        view = null
    }

    override fun onViewCreated() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getComingWeekWeather(isOffLineMode: Boolean,latitude: Double, longitude: Double, appid:String){
        view?.showLoader()
        interactor?.fetchComingWeekWeather(
            latitude,
            longitude,
            appid,
            this
        )
    }

    override fun onQueryGettingComingWeekWeatherFailed(error: Int) {
        view?.hideLoader()
        view?.showError(402)
    }

    override fun onQueryGettingComingWeekWeatherSucceed(response: CurrentGeographicCoordinatesWeatherResponse) {
        view?.hideLoader()
    }

    override fun getCurrentDayWeather(isOffLineMode: Boolean,latitude: Double, longitude: Double, appid:String) {
        view?.showLoader()
        interactor?.fetchComingWeekWeather(latitude, longitude, appid, this)
    }

    override fun onQueryGettingCurrentDayWeatherFailed() {
        view?.hideLoader()
        view?.showError(402)
    }

    override fun onQueryGettingCurrentDayWeatherSucceed() {
        view?.hideLoader()
    }
}