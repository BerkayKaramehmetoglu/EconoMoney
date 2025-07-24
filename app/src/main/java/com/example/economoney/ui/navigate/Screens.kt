package com.example.economoney.ui.navigate

import com.example.economoney.data.entity.Coins
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens(val router: String) {
    @Serializable
    data object Home : Screens("home_screen")

    @Serializable
    data class Detail(
        val coins: Coins
    ) : Screens("detail_screen")

    @Serializable
    data object Trend : Screens("trend_screen")

    @Serializable
    data object Settings : Screens("settings_screen")
}