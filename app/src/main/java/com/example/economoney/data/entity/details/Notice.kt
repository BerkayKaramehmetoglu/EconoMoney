package com.example.economoney.data.entity.details

import kotlinx.serialization.Serializable

@Serializable
data class Notice(
    val type: String?,
    val value: String?
)
