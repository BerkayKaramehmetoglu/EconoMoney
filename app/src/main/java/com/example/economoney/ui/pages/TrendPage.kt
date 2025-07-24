package com.example.economoney.ui.pages

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.economoney.utils.Utils
import com.example.economoney.viewmodels.HomeViewModel
import com.example.economoney.viewmodels.TrendViewModel

@Composable
fun TrendPage(
    navHostController: NavHostController,
    trendViewModel: TrendViewModel,
    homeViewModel: HomeViewModel
) {
    val coins by trendViewModel.trendCoinsList
    val time by homeViewModel.time.collectAsState()

    LaunchedEffect(time) {
        trendViewModel.getTrendCoins(limit = 100, time)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(coins) { coin ->
            val cleanedCoin = coin.copy(sparkline = coin.sparkline?.filterNotNull())
            if (coin.change != null && coin.price != null) {
                Utils.ListOfCoins(navHostController, cleanedCoin, coin, coin.price, coin.change)
            }
        }
    }
}