package com.example.economoney.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.economoney.viewmodels.HomeViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.economoney.R

@SuppressLint("DefaultLocale")
@Composable
fun HomePage(
    homeViewModel: HomeViewModel
) {
    val coins by homeViewModel.coinsList

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(coins) { coin ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                )
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    coin.iconUrl?.let { image ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image)
                                .crossfade(true)
                                .build(),
                            contentDescription = coin.name,
                            modifier = Modifier
                                .size(56.dp)
                                .padding(8.dp),
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    coin.name?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(R.color.black)
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    coin.symbol?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Light,
                            color = colorResource(R.color.black)
                        )
                    }
                    coin.rank?.let {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp),
                            text = it.toString(),
                            fontWeight = FontWeight.ExtraLight,
                            color = colorResource(R.color.gray),
                            textAlign = TextAlign.End
                        )
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.light_gray),
                    ),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp, bottom = 16.dp)
                ) {
                    coin.price?.let {
                        val priceDouble = coin.price.toDouble()
                        val formattedString = String.format("%.2f", priceDouble)
                        Text(
                            text = "$$formattedString",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                            color = colorResource(R.color.black),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}