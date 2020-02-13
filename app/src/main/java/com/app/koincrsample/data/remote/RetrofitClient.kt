package com.app.koincrsample.data.remote

import com.app.koincrsample.BuildConfig
import com.app.koincrsample.data.model.custom.ResponseHandler
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @AUTHOR Pushkar Srivastava
 * @date 11/02/2020
 */

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideForecastApi(get()) }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }
    factory { ResponseHandler() }
}


private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

private fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    return logger
}


private fun provideForecastApi(retrofit: Retrofit): APICallMethods =
    retrofit.create(APICallMethods::class.java)


class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // DONT INCLUDE API KEYS IN YOUR SOURCE CODE
        val url = req.url.newBuilder()./*addQueryParameter("APPID", BuildConfig.API_KEY).*/build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}

