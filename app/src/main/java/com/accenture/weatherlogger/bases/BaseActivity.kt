package com.accenture.weatherlogger.bases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
}
