package com.example.economoney.ui.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.economoney.viewmodels.HomeViewModel
import androidx.compose.foundation.lazy.items

@Composable
fun HomePage(
    homeViewModel: HomeViewModel
) {
    val coins by homeViewModel.coinsList

    LazyColumn{
        items(coins) { coin ->
            coin.name?.let { Text(text = it) }
        }
    }

    Text(text = "Home Page")
}