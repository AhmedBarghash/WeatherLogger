package com.accenture.weatherlogger.bases

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.accenture.weatherlogger.R

open class BaseActivity : AppCompatActivity() {

    protected fun showErrorMessage(code: Int){
        when (code){
            400 -> {showMessage(applicationContext.resources.getString(R.string.your_token_is_expired))}
            401 -> {showMessage(applicationContext.resources.getString(R.string.end_point_not_found))}
            101 -> {showMessage(applicationContext.resources.getString(R.string.timeout_message))}
            else -> {
                showMessage(applicationContext.resources.getString(R.string.somethin_is_wrong))
            }
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
