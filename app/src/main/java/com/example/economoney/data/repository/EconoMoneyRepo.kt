package com.example.economoney.data.repository

import com.example.economoney.data.datasource.EconoMoneyDataSource
import com.example.economoney.data.entity.Coins
import javax.inject.Inject

class EconoMoneyRepo @Inject constructor(var econoMoneyDataSource: EconoMoneyDataSource) {

    suspend fun getCoins(
        limit: Int,
        time: String,
        orderDirection: String,
        orderBy: String
    ): List<Coins> =
        econoMoneyDataSource.getCoins(limit, time, orderDirection, orderBy)
}