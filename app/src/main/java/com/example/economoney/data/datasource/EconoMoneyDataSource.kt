package com.example.economoney.data.datasource

import com.example.economoney.data.entity.coins.AllCoins
import com.example.economoney.data.entity.coins.Coins
import com.example.economoney.data.entity.details.DetailAllCoin
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

    suspend fun getTrendCoins(limit: Int, time: String): AllCoins = withContext(Dispatchers.IO) {
        return@withContext apiServicesDAO.getTrendCoins(limit, time)
    }

    suspend fun getDetails(uuid: String, time: String): DetailAllCoin =
        withContext(Dispatchers.IO) {
            return@withContext apiServicesDAO.getDetails(uuid, time)
        }
}