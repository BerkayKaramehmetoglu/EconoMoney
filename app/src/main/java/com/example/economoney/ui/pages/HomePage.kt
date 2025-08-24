package com.example.economoney.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.economoney.viewmodels.HomeViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.economoney.utils.Utils

@SuppressLint("DefaultLocale")
@Composable
fun HomePage(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel
) {
    val coins by homeViewModel.coinsList
    val time by homeViewModel.time.collectAsState()
    val orderDirection by homeViewModel.orderDirection.collectAsState()
    val orderBy by homeViewModel.oderBy.collectAsState()

    LaunchedEffect(time, orderDirection, orderBy) {
        homeViewModel.getCoins(limit = 100, time, orderDirection, orderBy)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(coins) { coin ->
            val cleanedCoin = coin.copy(sparkline = coin.sparkline?.filterNotNull())
            if (coin.change != null && coin.price != null) {
                Utils.ListOfCoins(
                    navHostController,
                    cleanedCoin,
                    coin,
                    coin.price,
                    coin.change
                )
            }
        }
    }
}