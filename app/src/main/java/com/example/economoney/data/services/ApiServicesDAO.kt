package com.example.economoney.data.services

import com.example.economoney.data.entity.AllCoins
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicesDAO {

    @GET("coins")
    suspend fun getCoins(
        @Query("timePeriod") time: String
    ): AllCoins
}