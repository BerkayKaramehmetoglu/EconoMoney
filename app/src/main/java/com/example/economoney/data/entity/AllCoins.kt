package com.example.economoney.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class AllCoins(
    val status: String,
    val data: Data
)