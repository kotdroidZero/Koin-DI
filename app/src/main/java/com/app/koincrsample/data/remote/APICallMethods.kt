package com.app.koincrsample.data.remote

import com.app.koincrsample.data.model.response.UserListResponse
import com.app.koincrsample.data.model.response.Weather
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */
interface APICallMethods {

    @GET("weather")
    suspend fun getForecast(@Query("q") location: String, @Query("units") s1: String): Weather


    @GET("/api/users?page=2")
    suspend fun getUsers(): UserListResponse

/*
    @GET("/api/users?delay=3")
    suspend fun getDelayedResponse():*/
}