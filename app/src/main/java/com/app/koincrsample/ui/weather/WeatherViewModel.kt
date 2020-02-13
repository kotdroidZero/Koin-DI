package com.app.koincrsample.ui.weather

import androidx.lifecycle.viewModelScope
import com.app.koincrsample.base.BaseViewModel
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
    factory { WeatherViewModel(get()) }
}

class WeatherViewModel(private val dataRepository: DataRepository) :
    BaseViewModel<WeatherViewActor>() {

    fun getWeather(input: String) {
        viewModelScope.launch {
            getLoading().postValue(true)
            var response: Resource<Weather>? = null
            async {
                response = dataRepository.getWeather(input)
            }.join()
            processResponse(response!!)
        }
    }
}