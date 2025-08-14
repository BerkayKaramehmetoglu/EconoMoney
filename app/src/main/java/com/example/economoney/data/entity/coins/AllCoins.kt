package com.example.economoney.data.entity.coins

import kotlinx.serialization.Serializable

@Serializable
data class AllCoins(
    val status: String,
    val data: Data
)