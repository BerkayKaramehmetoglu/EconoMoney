package com.example.economoney.data.services

import com.example.economoney.data.entity.AllCoins
import retrofit2.http.GET

interface ApiServicesDAO {

    @GET("coins")
    suspend fun getCoins() : AllCoins
}