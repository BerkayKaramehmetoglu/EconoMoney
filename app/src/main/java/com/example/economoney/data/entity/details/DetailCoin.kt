package com.example.economoney.data.entity.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailCoin(
    val uuid: String?,
    val symbol: String?,
    val name: String?,
    val description: String?,
    val color: String?,
    val iconUrl: String?,
    val websiteUrl: String?,
    val links: List<Links>?,
    val supply: Supply?,
    val numberOfMarkets: Int?,
    val numberOfExchanges: Int?,
    @SerialName("24hVolume") val volume24H: String?,
    val marketCap: String?,
    val fullyDilutedMarketCap: String?,
    val price: String?,
    val btcPrice: String?,
    val priceAt: Long?,
    val change: String?,
    val rank: Int?,
    val sparkline: List<String?>?,
    val allTimeHigh: AllTimeHigh?,
    val coinrankingUrl: String?,
    val tier: Int?,
    val lowVolume: Boolean?,
    val listedAt: Long?,
    val hasContent: Boolean?,
    val notices: List<Notice>?,
    val contractAddresses: List<String>?,
    val tags: List<String>?,
    val isWrappedTrustless: Boolean?,
    val wrappedTo: String?
)
