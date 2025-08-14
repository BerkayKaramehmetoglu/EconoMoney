package com.example.economoney.data.repository

import com.example.economoney.data.datasource.EconoMoneyDataSource
import com.example.economoney.data.entity.coins.AllCoins
import com.example.economoney.data.entity.coins.Coins
import com.example.economoney.data.entity.details.DetailAllCoin
import javax.inject.Inject

class EconoMoneyRepo @Inject constructor(var econoMoneyDataSource: EconoMoneyDataSource) {

    suspend fun getCoins(
        limit: Int,
        time: String,
        orderDirection: String,
        orderBy: String
    ): List<Coins> =
        econoMoneyDataSource.getCoins(limit, time, orderDirection, orderBy)

    suspend fun getTrendCoins(limit: Int, time: String): AllCoins =
        econoMoneyDataSource.getTrendCoins(limit, time)

    suspend fun getDetails(uuid: String, time: String): DetailAllCoin =
        econoMoneyDataSource.getDetails(uuid, time)
}