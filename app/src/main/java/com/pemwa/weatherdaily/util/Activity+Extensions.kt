package com.pemwa.weatherdaily.util

import android.app.Activity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.pemwa.weatherdaily.R

fun Activity.showErrorSnackBar(message: String, isAnError: Boolean) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).apply {
        val snackBarView = this.view
        when {
            isAnError -> {
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@showErrorSnackBar,
                        R.color.colorSnackBarError
                    )
                )
            }
            else -> {
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@showErrorSnackBar,
                        R.color.colorSnackBarSuccess
                    )
                )
            }
        }
    }.show()
}