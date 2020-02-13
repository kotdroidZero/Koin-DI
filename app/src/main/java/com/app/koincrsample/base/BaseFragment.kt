package com.app.koincrsample.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * @AUTHOR Pushkar Srivastava
 * @date 12/02/2020
 */
abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observeCommonData()
    }

    private fun observeCommonData() {


        observeData()
    }

    abstract fun observeData()

    abstract fun init()
}