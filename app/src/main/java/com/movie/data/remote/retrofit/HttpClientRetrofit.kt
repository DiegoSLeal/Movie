package com.movie.data.remote.retrofit

import android.app.Application
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClientRetrofit(private val androidApplication: Application) :
    HttpClientRetrofitInterface {

    override fun <T> startRetrofit(
        movieServiceClass: Class<T>
    ): T {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .cache(Cache(androidApplication.cacheDir, 10 * 1024 * 1024))
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS).build()
            )
            .build()
            .create(movieServiceClass)
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}
