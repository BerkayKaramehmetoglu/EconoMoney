package org.berkaykaramehmetoglu.economoney.ui

sealed class Screens(val router: String) {
    data object HomePage: Screens("home_screen")
    data object NewsPage: Screens("news_screen")
    data object SettingsPage: Screens("settings_screen")
}