package com.accenture.weatherlogger.bases

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.accenture.weatherlogger.R

open class BaseActivity : AppCompatActivity() {

    protected fun showErrorMessage(code: Int){
        when (code){
            401 -> {}
            403 -> {}
            405 -> {}
            404 -> {}
            101 -> {showMessage(applicationContext.resources.getString(R.string.duplicate_data_message))}
            else -> {

            }
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
