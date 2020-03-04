package com.accenture.weatherlogger.homemodule.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.accenture.weatherlogger.R
import com.accenture.weatherlogger.bases.BaseActivity
import com.accenture.weatherlogger.homemodule.HomeContract
import com.accenture.weatherlogger.homemodule.datalayer.database.dto.RecordedWeatherDto
import com.accenture.weatherlogger.homemodule.presenter.HomePresenter
import com.accenture.weatherlogger.homemodule.view.adapter.RecordedWeatherAdapter
import com.accenture.weatherlogger.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : BaseActivity(), HomeContract.View {

    private lateinit var userCurrentLocation: Location
    private lateinit var gpsTracker: GPSTracker
    private lateinit var adapter: RecordedWeatherAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recordedWeatherList: ArrayList<RecordedWeatherDto>
    private var presenter: HomeContract.Presenter? = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gpsTracker = GPSTracker(applicationContext)

        // check if there is a data in the data base. if yes show List view if no show add view.
        initializeRecordedWeatherList()
        updateViewWithNewSavedData()
        iv_add_button.setOnClickListener {
            if (gpsTracker.checkGPSStatus()) {
                getUserUpdatedLocation()
            } else
                gpsTracker.showGPSDisabledAlertToUser()
        }

        add_new_record.setOnClickListener {
            if (gpsTracker.checkGPSStatus()) {
                getUserUpdatedLocation()
            } else
                gpsTracker.showGPSDisabledAlertToUser()
        }
    }

    private fun getUserUpdatedLocation() {
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            createLocationListenerRequest()
        } else {
            gpsTracker.createRequestPermissions(this@HomeActivity)
        }
    }

    @SuppressLint("MissingPermission")
    fun createLocationListenerRequest() {
        gpsTracker.getLocation { location ->
            if (location != null) {
                userCurrentLocation = location
            }
            updateViewWithServerData()
        }
    }

    private fun updateViewWithServerData() {
        // TODO get the latitude and longitude from the mobile.
        if (!isNetworkAvailable(applicationContext)) {
            showMessage(applicationContext.resources.getString(R.string.no_internetConnection))
            return
        }
        if (userCurrentLocation != null) {
            presenter?.getCurrentDayWeather(
                latitude = userCurrentLocation.latitude,
                longitude = userCurrentLocation.longitude,
                context = applicationContext
            )
        }
//        presenter?.getCurrentDayWeather(
//            latitude = 31.040282,
//            longitude = 30.458077,
//            context = applicationContext
//        )
    }

    override fun updateViewWithNewSavedData() {
        presenter?.getOfflineWeatherData(applicationContext)
    }

    override fun notifyViewWithNewData(item: RecordedWeatherDto) {
        presenter?.addNewRecordedWeatherData(applicationContext, item)
    }

    override fun showAddingWeatherDataView() {
        showMessage(applicationContext.resources.getString(R.string.no_data_toshow))
        cl_no_recordView.visibility = View.VISIBLE
        rv_recorded_weather_list.visibility = View.GONE
        add_new_record.visibility = View.GONE
    }

    override fun presentOffLineWeatherData(offLineWeatherDataList: ArrayList<RecordedWeatherDto>) {
        cl_no_recordView.visibility = View.GONE
        rv_recorded_weather_list.visibility = View.VISIBLE
        add_new_record.visibility = View.VISIBLE
        recordedWeatherList.clear()
        recordedWeatherList.addAll(offLineWeatherDataList)
        adapter.notifyDataSetChanged()
    }

    private fun initializeRecordedWeatherList() {
        recordedWeatherList = ArrayList()
        layoutManager = LinearLayoutManager(applicationContext)
        adapter = RecordedWeatherAdapter(applicationContext, recordedWeatherList, this)
        rv_recorded_weather_list.layoutManager = layoutManager
        rv_recorded_weather_list.adapter = adapter
    }

    override fun onRecordedWeatherSelected(item: RecordedWeatherDto) {
        presenter?.readyToShowDetailsScreen(item, applicationContext)
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

    override fun onDestroy() {
        if (presenter != null) {
            presenter?.onDestroy()
            presenter = null
        }
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == gpsTracker.REQUEST_FINE_LOCATION && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
            createLocationListenerRequest()
        }
    }
}
