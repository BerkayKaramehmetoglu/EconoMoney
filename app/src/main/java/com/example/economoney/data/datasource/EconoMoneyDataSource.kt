package com.example.economoney.data.datasource

import com.example.economoney.data.entity.Coins
import com.example.economoney.data.services.ApiServicesDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EconoMoneyDataSource @Inject constructor(var apiServicesDAO: ApiServicesDAO) {

    suspend fun getCoins(
        limit: Int,
        time: String,
        orderDirection: String,
        orderBy: String
    ): List<Coins> =
        withContext(Dispatchers.IO) {
            return@withContext apiServicesDAO.getCoins(
                limit,
                time,
                orderDirection,
                orderBy
            ).data.coins
        }
}