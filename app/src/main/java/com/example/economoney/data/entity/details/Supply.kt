package com.example.economoney.data.entity.details

import kotlinx.serialization.Serializable

@Serializable
data class Supply(
    val confirmed: Boolean?,
    val supplyAt: Long?,
    val max: String?,
    val total: String?,
    val circulating: String?
)
