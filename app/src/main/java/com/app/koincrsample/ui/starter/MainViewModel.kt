package com.app.koincrsample.ui.starter

import com.app.koincrsample.base.BaseViewModel
import com.app.koincrsample.data.local.prefs.PrefManager
import com.app.koincrsample.data.remote.DataRepository
import org.koin.dsl.module

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */


val mainViewModelModule = module {
    factory { MainViewModel(get(), get()) }
}

class MainViewModel(prefsManager: PrefManager, private val dataRepository: DataRepository) :
    BaseViewModel<MainViewActor>()