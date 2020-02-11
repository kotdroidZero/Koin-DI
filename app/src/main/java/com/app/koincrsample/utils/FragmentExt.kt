package com.app.koincrsample.utils

/**
 * Extension functions for Fragment.
 */

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showToast(resId: Int? = null, message: String? = null) {
    Toast.makeText(
        activity, if (resId != null) {
            activity!!.getString(resId)
        } else {
            message!!
        }, Toast.LENGTH_SHORT
    ).show()

}