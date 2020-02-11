package com.app.koincrsample.utils


import android.app.Activity
import android.widget.Toast


fun Activity.showToast(resId: Int? = null, message: String? = null) {
    Toast.makeText(
        this, if (resId != null) {
            this.getString(resId)
        } else {
            message!!
        }, Toast.LENGTH_SHORT
    ).show()

}
