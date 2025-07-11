package com.example.economoney.ui.navigate

sealed class Screens(val router: String){
    data object Home: Screens("home_screen")
    data object Settings: Screens("settings_screen")
}