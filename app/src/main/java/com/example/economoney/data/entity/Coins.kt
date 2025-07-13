package com.example.economoney.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coins(
    val uuid: String?,
    val symbol: String?,
    val name: String?,
    val color: String?,
    val iconUrl: String?,
    val marketCap: String?,
    val price: String?,
    val listedAt: Int?,
    val tier: Byte?,
    val change: String?,
    val rank: Byte?,
    val sparkline: List<String>?,
    val lowVolume: Boolean?,
    val coinrankingUrl: String?,
    @SerialName("24hVolume") val volume24H: String?,
    val btcPrice: String?,
    val contractAddresses: List<String>?,
    val isWrappedTrustless: Boolean?,
    val wrappedTo: String?
)
