package com.example.economoney.ui.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.economoney.ui.navigate.Screens

@Composable
fun DetailPage(args: Screens.Detail) {
    Text(text = "${args.coins.price} Detail Page")
}