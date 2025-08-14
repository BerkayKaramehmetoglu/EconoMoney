package com.example.economoney.data.entity.coins

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val stats: Stats,
    val coins: List<Coins>
)
