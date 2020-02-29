package com.accenture.weatherlogger.homemodule.presenter

import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.interactour.HomeInteractor

class HomePresenter(private var view: HomeContract.View?) : HomeContract.Presenter,HomeContract.InteractorOutput {

    private var  interactor: HomeContract.Interactor? = HomeInteractor()

    override fun onDestroy() {
        interactor = null
        view =  null
    }

    override fun onViewCreated() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}