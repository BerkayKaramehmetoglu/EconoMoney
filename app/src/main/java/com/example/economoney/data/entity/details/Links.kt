package com.example.economoney.data.entity.details

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val name: String?,
    val url: String?,
    val type: String?
)
