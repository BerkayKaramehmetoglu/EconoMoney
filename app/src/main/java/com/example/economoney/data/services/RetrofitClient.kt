package com.example.economoney.data.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getClient(baseUrl: String): Retrofit {
            val client = OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }
}