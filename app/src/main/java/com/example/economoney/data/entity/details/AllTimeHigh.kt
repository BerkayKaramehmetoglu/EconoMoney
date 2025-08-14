package com.example.economoney.data.entity.details

import kotlinx.serialization.Serializable

@Serializable
data class AllTimeHigh(
    val price: String?,
    val timestamp: Long?
)