package com.app.koincrsample.utils

/**
 * Extension functions for Fragment.
 */

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * @AUTHOR Pushkar Srivastava
 * @date 08/02/2020
 */
fun Fragment.showToast(resId: Int? = null, message: String? = null) {
    Toast.makeText(
        activity, if (resId != null) {
            activity!!.getString(resId)
        } else {
            message!!
        }, Toast.LENGTH_SHORT
    ).show()

}