package com.example.economoney.ui.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.economoney.ui.pages.DetailPage
import com.example.economoney.ui.pages.HomePage
import com.example.economoney.ui.pages.SettingsPage
import com.example.economoney.ui.pages.TrendPage
import com.example.economoney.viewmodels.DetailViewModel
import com.example.economoney.viewmodels.HomeViewModel
import com.example.economoney.viewmodels.TrendViewModel

@Composable
fun SetUpNavHost(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    trendViewModel: TrendViewModel,
    detailViewModel: DetailViewModel
) {
    NavHost(navHostController, startDestination = Screens.Home.router) {
        composable(Screens.Home.router) {
            HomePage(navHostController = navHostController, homeViewModel = homeViewModel)
        }

        composable<Screens.Detail> {
            val args = it.toRoute<Screens.Detail>()
            DetailPage(
                args = args.uuid,
                detailViewModel = detailViewModel,
                homeViewModel = homeViewModel
            )
        }

        composable(Screens.Trend.router) {
            TrendPage(
                navHostController = navHostController,
                trendViewModel = trendViewModel,
                homeViewModel = homeViewModel
            )
        }

        composable(Screens.Settings.router) {
            SettingsPage()
        }
    }
}