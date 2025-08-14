package com.example.economoney.data.services

import com.example.economoney.data.entity.coins.AllCoins
import com.example.economoney.data.entity.details.DetailAllCoin
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServicesDAO {

    @GET("coins")
    suspend fun getCoins(
        @Query("limit") limit: Int,
        @Query("timePeriod") time: String,
        @Query("orderDirection") orderDirection: String,
        @Query("orderBy") orderBy: String,
    ): AllCoins

    @GET("coins/trending")
    suspend fun getTrendCoins(
        @Query("limit") limit: Int,
        @Query("timePeriod") time: String
    ): AllCoins

    @GET("coin/{uuid}")
    suspend fun getDetails(
        @Path("uuid") uuid: String,
        @Query("timePeriod") time: String
    ): DetailAllCoin

}