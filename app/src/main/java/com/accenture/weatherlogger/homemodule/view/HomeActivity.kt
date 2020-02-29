package com.accenture.weatherlogger.homemodule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.accenture.weatherlogger.R
import com.accenture.weatherlogger.bases.BaseActivity
import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.presenter.HomePresenter

class HomeActivity : BaseActivity(), HomeContract.View {

    private var presenter: HomeContract.Presenter? = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showError(code: Int) {
        showErrorMessage(code)
    }

    override fun viewLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        if (presenter != null) {
            presenter?.onDestroy()
            presenter = null
        }
        super.onDestroy()
    }
}
