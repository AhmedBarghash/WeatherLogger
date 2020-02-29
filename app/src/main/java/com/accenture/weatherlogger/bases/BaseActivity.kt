package com.accenture.weatherlogger.bases

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    protected fun showErrorMessage(code: Int){
        when (code){
            401 -> {}
            403 -> {}
            405 -> {}
            404 -> {}
            else -> {

            }
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    protected open fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
