package com.movie.data.remote.retrofit

interface HttpClientRetrofitInterface {

    fun <T> startRetrofit(movieServiceClass: Class<T>): T
}
