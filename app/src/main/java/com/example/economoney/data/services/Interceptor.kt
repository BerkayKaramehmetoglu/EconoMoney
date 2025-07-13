package com.example.economoney.data.services

import okhttp3.Interceptor
import okhttp3.Response
import com.example.economoney.BuildConfig

class ApiKeyInterceptor : Interceptor {
    val apiKey = BuildConfig.API_KEY
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                "x-access-token",
                apiKey
            )
            .build()
        return chain.proceed(request)
    }
}
