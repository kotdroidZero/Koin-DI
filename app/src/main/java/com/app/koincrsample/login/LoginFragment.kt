package com.app.koincrsample.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.koincrsample.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */

/**
 * factory definition creates new instance each times it required
 */
val loginFragmentModule = module {
    factory { LoginFragment() }
}

class LoginFragment : Fragment() {


    val exampleViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


}
