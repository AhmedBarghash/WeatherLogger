package com.accenture.weatherlogger.homemodule

import CurrentGeographicCoordinatesWeatherResponse

interface HomeContract {
    interface View {
        fun showError(code: Int)
        fun showLoader ()
        fun hideLoader ()
        fun showComingWeekWeather ()
        fun showCurrentDayWeather ()
    }

    interface Presenter {
        fun onDestroy ()
        fun onViewCreated ()
        fun getComingWeekWeather (isOffLineMode: Boolean,latitude: Double, longitude: Double, appid:String)
        fun getCurrentDayWeather (isOffLineMode: Boolean,latitude: Double, longitude: Double, appid:String)
    }

    interface InteractorOutput {
        fun onQueryGettingComingWeekWeatherFailed(error: Int)
        fun onQueryGettingComingWeekWeatherSucceed(response: CurrentGeographicCoordinatesWeatherResponse)
        fun onQueryGettingCurrentDayWeatherFailed()
        fun onQueryGettingCurrentDayWeatherSucceed()
    }

    interface Interactor {
        fun fetchComingWeekWeather (
            latitude: Double,
            longitude: Double,
            appid: String,
            callBack: InteractorOutput
        )
        fun fetchCurrentDayWeather (latitude: Double, longitude: Double, appid:String)
    }
}