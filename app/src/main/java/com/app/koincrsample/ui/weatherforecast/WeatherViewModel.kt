package com.app.koincrsample.ui.weatherforecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.koincrsample.base.BaseViewModel
import com.app.koincrsample.data.local.prefs.PrefManager
import com.app.koincrsample.data.model.custom.Resource
import com.app.koincrsample.data.model.response.Weather
import com.app.koincrsample.data.remote.DataRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.dsl.module


/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */


val weatherViewModelModule = module {
    factory { WeatherViewModel(get(), get()) }
}

class WeatherViewModel(prefsManager: PrefManager, private val weatherRepository: DataRepository) :
    BaseViewModel() {


    private val location = MutableLiveData<String>()

    val weather = MutableLiveData<Resource<Weather>>()

//    var weather = location.switchMap { location ->
//        liveData(Dispatchers.IO) {
//            emit(Resource.loading(null))
//            emit(weatherRepository.getWeather(location))
//            emit(Resource.loading(null))
//        }
//    }

    fun getWeather(input: String) {
        viewModelScope.launch {
            weather.postValue(Resource.loading(null))
            var response: Resource<Weather>? = null
            async {
                response = weatherRepository.getWeather(input)
            }.join()
            weather.postValue(response)
//            weather.postValue(Resource.loading(null))
        }
    }
}