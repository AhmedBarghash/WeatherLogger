package com.accenture.weatherlogger.homemodule.view

import android.os.Bundle
import android.view.View
import com.accenture.weatherlogger.Constants
import com.accenture.weatherlogger.R
import com.accenture.weatherlogger.bases.BaseActivity
import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_main.loader_bg

class HomeActivity : BaseActivity(), HomeContract.View {

    private var presenter: HomeContract.Presenter? = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO get the latitude and longitude from the mobile.
        presenter?.getCurrentDayWeather(isOffLineMode = isNetworkAvailable(),latitude = 31.040282,longitude = 30.458077,appid = Constants.appIdKey)
    }

    override fun showError(code: Int) {
        showErrorMessage(code)
    }

    override fun showLoader() {
        loader_bg.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        loader_bg.visibility = View.GONE
    }

    override fun showComingWeekWeather() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCurrentDayWeather() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // destroy
    override fun onDestroy() {
        if (presenter != null) {
            presenter?.onDestroy()
            presenter = null
        }
        super.onDestroy()
    }
}
