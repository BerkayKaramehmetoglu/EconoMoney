package com.example.economoney.ui.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.economoney.ui.pages.HomePage
import com.example.economoney.ui.pages.SettingsPage

@Composable
fun SetUpNavHost(navHostController: NavHostController) {
    NavHost(navHostController, startDestination = Screens.Home.router) {
        composable(Screens.Home.router) {
            HomePage()
        }
        composable(Screens.Settings.router) {
            SettingsPage()
        }
    }
}