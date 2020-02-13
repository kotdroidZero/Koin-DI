package com.app.koincrsample.utils

import android.app.Application
import com.app.koincrsample.data.local.prefs.prefModule
import com.app.koincrsample.data.remote.networkModule
import com.app.koincrsample.data.remote.repositoryModule
import com.app.koincrsample.ui.login.loginFragmentModule
import com.app.koincrsample.ui.login.loginViewModelModule
import com.app.koincrsample.ui.userlist.userListFragmentModule
import com.app.koincrsample.ui.userlist.userListViewModelModule
import com.app.koincrsample.ui.userlist.usersListViewModelModule
import com.app.koincrsample.ui.weatherforecast.weatherFragmentModule
import com.app.koincrsample.ui.weatherforecast.weatherViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @AUTHOR Pushkar Srivastava
 * @date 08/02/2020
 */
class ApplicationGlobal : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ApplicationGlobal)
            modules(
                listOf(
                    prefModule, networkModule, repositoryModule,
                    loginFragmentModule, loginViewModelModule,
                    weatherFragmentModule, weatherViewModelModule,
                    userListFragmentModule, usersListViewModelModule
                )
            )
        }

    }
}