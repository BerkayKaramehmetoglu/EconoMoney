package com.example.economoney.ui.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.economoney.data.entity.Coins
import com.example.economoney.ui.pages.DetailPage
import com.example.economoney.ui.pages.HomePage
import com.example.economoney.ui.pages.SettingsPage
import com.example.economoney.viewmodels.HomeViewModel
import kotlin.reflect.typeOf

@Composable
fun SetUpNavHost(navHostController: NavHostController, homeViewModel: HomeViewModel) {
    NavHost(navHostController, startDestination = Screens.Home.router) {
        composable(Screens.Home.router) {
            HomePage(navHostController = navHostController, homeViewModel = homeViewModel)
        }
        composable<Screens.Detail>(
            typeMap = mapOf(
                typeOf<Coins>() to CustomNavType.CoinsType
            )
        ) {
            val args = it.toRoute<Screens.Detail>()
            DetailPage(args)
        }
        composable(Screens.Settings.router) {
            SettingsPage()
        }
    }
}