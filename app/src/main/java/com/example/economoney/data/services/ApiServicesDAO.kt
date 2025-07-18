package com.example.economoney.data.services

import com.example.economoney.data.entity.AllCoins
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicesDAO {

    @GET("coins")
    suspend fun getCoins(
        @Query("limit") limit: Int,
        @Query("timePeriod") time: String,
        @Query("orderDirection") orderDirection: String,
        @Query("orderBy") orderBy: String,
    ): AllCoins
}