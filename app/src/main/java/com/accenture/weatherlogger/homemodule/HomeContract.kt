package com.accenture.weatherlogger.homemodule

interface HomeContract {
    interface View {
        fun showError(code: Int)
        fun viewLoader ()
        fun hideLoader ()
    }

    interface Presenter {
        fun onDestroy ()
        fun onViewCreated ()
    }

    interface InteractorOutput {
    }

    interface Interactor {
    }
}