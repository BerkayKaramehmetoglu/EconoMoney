package com.example.economoney.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.economoney.R
import com.example.economoney.ui.charts.LineCharts
import com.example.economoney.ui.navigate.Screens
import com.example.economoney.utils.Utils

@Composable
fun DetailPage(args: Screens.Detail) {
    var color = Color.Black
    Utils.coinColor(args.coins) { colors -> color = colors }

    var minValue: Float? = null
    var maxValue: Float? = null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 240.dp, height = 400.dp),
            colors = CardDefaults.cardColors(
                containerColor = color.copy(alpha = 0.5f),
            ),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 32.dp,
                bottomEnd = 32.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                Row {
                    args.coins.name?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(R.color.black),
                            fontSize = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    args.coins.symbol?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Light,
                            color = colorResource(R.color.black),
                            fontSize = 24.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.size(24.dp))
                args.coins.price?.let {
                    val priceDouble = args.coins.price.toDouble()
                    val formattedString = String.format("%.2f", priceDouble)
                    Text(
                        text = "$$formattedString",
                        color = colorResource(R.color.black),
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                args.coins.change?.let {
                    Text(
                        text = "${it}%",
                        modifier = Modifier
                            .padding(top = 4.dp),
                        color = if (args.coins.change.toDouble() < 0)
                            colorResource(R.color.red)
                        else
                            colorResource(R.color.green),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.size(24.dp))
                LineCharts(
                    coins = args.coins,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(97.dp)
                        .align(Alignment.CenterHorizontally),
                    color = color.copy(0.5f),
                    Ymin = { minValue = it },
                    Ymax = { maxValue = it }
                )
            }
        }
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.white),
            ),
            border = BorderStroke(2.dp, color = colorResource(R.color.black)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .size(width = 240.dp, height = 200.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    args.coins.iconUrl?.let { image ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image)
                                .crossfade(true)
                                .build(),
                            contentDescription = args.coins.name,
                            modifier = Modifier
                                .size(48.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        maxValue?.let {
                            Text(
                                text = "Max Price: $$it",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.black),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(8.dp),
                    thickness = 2.dp,
                    color = colorResource(R.color.black)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    args.coins.iconUrl?.let { image ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image)
                                .crossfade(true)
                                .build(),
                            contentDescription = args.coins.name,
                            modifier = Modifier
                                .size(48.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        minValue?.let {
                            Text(
                                text = "Min Price: $$it",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.black),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(8.dp),
                    thickness = 2.dp,
                    color = colorResource(R.color.black)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    args.coins.iconUrl?.let { image ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image)
                                .crossfade(true)
                                .build(),
                            contentDescription = args.coins.name,
                            modifier = Modifier
                                .size(48.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        args.coins.marketCap?.let {
                            Text(
                                text = "Market Cap: $it",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.black),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}