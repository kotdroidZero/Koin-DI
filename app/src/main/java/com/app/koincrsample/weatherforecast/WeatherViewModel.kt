package com.app.koincrsample.weatherforecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.app.koincrsample.base.BaseViewModel
import com.app.koincrsample.data.local.prefs.PrefManager
import com.app.koincrsample.data.model.custom.Resource
import com.app.koincrsample.data.remote.DataRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */


val weatherViewModelModule = module {
    factory { WeatherViewModel(get(), get()) }
}

class WeatherViewModel(
    prefsManager: PrefManager,
    private val weatherRepository: DataRepository
) : BaseViewModel( weatherRepository) {


    private val location = MutableLiveData<String>()


    var weather = location.switchMap { location ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(weatherRepository.getWeather(location))
            emit(Resource.loading(null))
        }
    }

    fun getWeather(input: String) {
        location.value = input
    }
}