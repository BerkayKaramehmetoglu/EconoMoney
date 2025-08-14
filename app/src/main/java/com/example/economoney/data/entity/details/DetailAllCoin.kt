package com.example.economoney.data.entity.details

import kotlinx.serialization.Serializable

@Serializable
data class DetailAllCoin(
    val status: String?,
    val data: DetailData
)
