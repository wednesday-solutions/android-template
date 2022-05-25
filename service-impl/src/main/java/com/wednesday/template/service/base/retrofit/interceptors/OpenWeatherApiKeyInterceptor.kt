package com.wednesday.template.service.base.retrofit.interceptors

import com.wednesday.template.service.impl.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class OpenWeatherApiKeyInterceptor : Interceptor {

    companion object {
        private const val APP_ID = "appid"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val openWeatherApiKey = BuildConfig.OPEN_WEATHER_API_KEY
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(APP_ID, openWeatherApiKey).build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}
