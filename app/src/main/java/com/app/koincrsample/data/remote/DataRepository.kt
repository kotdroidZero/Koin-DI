package com.app.koincrsample.data.remote

import com.app.koincrsample.data.model.custom.Resource
import com.app.koincrsample.data.model.custom.ResponseHandler
import com.app.koincrsample.data.model.response.UserListResponse
import com.app.koincrsample.data.model.response.Weather
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.koin.dsl.module

val repositoryModule = module {
    single { DataRepository(get(), get()) }
}

class DataRepository(
    private val apiCallMethods: APICallMethods, private val responseHandler: ResponseHandler
) {
    suspend fun getWeather(location: String): Resource<Weather> {
        return try {
            return responseHandler.handleSuccess(apiCallMethods.getForecast(location, "metric"))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getUsers(): Resource<UserListResponse> {
        return try {
            return responseHandler.handleSuccess(apiCallMethods.getUsers())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun fetchWeatherForecast() = flow {
//        emit(Resource.Loading)
        // Fake api call
        delay(1000)
        // Send a random fake weather forecast data
        emit(responseHandler.handleSuccess((0..20).random()))
    }

}