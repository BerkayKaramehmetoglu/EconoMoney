package com.example.economoney.data.repository

import com.example.economoney.data.datasource.EconoMoneyDataSource
import com.example.economoney.data.entity.Coins
import javax.inject.Inject

class EconoMoneyRepo @Inject constructor(var econoMoneyDataSource: EconoMoneyDataSource) {

    suspend fun getCoins(time: String): List<Coins> = econoMoneyDataSource.getCoins(time)
}