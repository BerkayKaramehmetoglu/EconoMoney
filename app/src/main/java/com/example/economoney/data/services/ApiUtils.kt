package com.example.economoney.data.services

class ApiUtils {
    companion object {
        val BASE_URL = "https://api.coinranking.com/v2/"

        fun apiServicesDAO(): ApiServicesDAO {
            return RetrofitClient.getClient(BASE_URL).create(ApiServicesDAO::class.java)
        }
    }
}