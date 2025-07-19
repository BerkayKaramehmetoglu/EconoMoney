package com.example.economoney.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    val total: Int?,
    val totalCoins: Int?,
    val totalMarkets: Int?,
    val totalExchanges: Int?,
    val totalMarketCap: String?,
    val total24hVolume: String?,
)
