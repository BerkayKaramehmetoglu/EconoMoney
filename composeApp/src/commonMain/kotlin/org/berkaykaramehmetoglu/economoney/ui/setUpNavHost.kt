package org.berkaykaramehmetoglu.economoney.ui

import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun SetUpNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HomePage.router) {
        composable(Screens.HomePage.router) {
            HomePage()
        }
        composable(Screens.NewsPage.router) {
            NewsPage()
        }
        composable(Screens.SettingsPage.router) {
            SettingsPage()
        }
    }
}
