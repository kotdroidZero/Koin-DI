package com.app.koincrsample.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import org.koin.dsl.module

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */


val loginViewModelModule = module {
    factory { LoginViewModel(get()) }
}

class LoginViewModel(preferences: SharedPreferences) : ViewModel()