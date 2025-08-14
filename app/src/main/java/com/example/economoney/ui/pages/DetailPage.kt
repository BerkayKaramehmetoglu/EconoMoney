package com.example.economoney.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.economoney.R
import com.example.economoney.ui.charts.LineCharts
import com.example.economoney.utils.Utils
import com.example.economoney.viewmodels.DetailViewModel
import com.example.economoney.viewmodels.HomeViewModel

@Composable
fun DetailPage(args: String, detailViewModel: DetailViewModel, homeViewModel: HomeViewModel) {
    val detailCoins by detailViewModel.detailCoinsList
    val uuid by detailViewModel.uuid.collectAsState()
    val time by homeViewModel.time.collectAsState()

    LaunchedEffect(uuid, time) {
        detailViewModel.getDetailCoins(uuid = args, time = time)
    }

    var color = Color.Black
    Utils.coinColor(detailCoins?.color) { colors -> color = colors }

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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    detailCoins?.iconUrl?.let { image ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image)
                                .crossfade(true)
                                .build(),
                            contentDescription = detailCoins?.name,
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    detailCoins?.name?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(R.color.black),
                            fontSize = 24.sp,
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    detailCoins?.symbol?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Light,
                            color = colorResource(R.color.black),
                            fontSize = 24.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                detailCoins?.price?.let {
                    val priceDouble = detailCoins!!.price!!.toDouble()
                    val formattedString = String.format("%.2f", priceDouble)
                    Text(
                        text = "$$formattedString",
                        color = colorResource(R.color.black),
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                detailCoins?.change?.let {
                    Text(
                        text = "${it}%",
                        modifier = Modifier
                            .padding(top = 4.dp),
                        color = if (detailCoins!!.change?.toDouble()!! < 0)
                            colorResource(R.color.red)
                        else
                            colorResource(R.color.green),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                LineCharts(
                    sparkLine = detailCoins?.sparkline,
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
        Spacer(modifier = Modifier.size(8.dp))
        detailCoins?.description?.let {
            Text(
                modifier = Modifier.padding(16.dp),
                text = it,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.black),
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            text = "Detail",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.white),
            ),
            border = BorderStroke(2.dp, color = colorResource(R.color.black)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .size(width = 240.dp, height = 220.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Website: ",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )
                    detailCoins?.websiteUrl?.let {
                        val uriHandler = LocalUriHandler.current
                        Text(
                            text = it,
                            color = colorResource(R.color.black),
                            textDecoration = TextDecoration.Underline,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .clickable {
                                    uriHandler.openUri(it)
                                }
                        )
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
                    Text(
                        text = "All Time High: ",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )
                    detailCoins?.allTimeHigh?.price?.let {
                        val priceDouble = it.toDouble()
                        val formattedString = String.format("%.2f", priceDouble)
                        Text(
                            text = "$$formattedString",
                            color = colorResource(R.color.black),
                            fontSize = 18.sp,
                        )
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
                    Text(
                        text = "Max Value: ",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )
                    maxValue?.let {
                        Text(
                            text = "$$it",
                            color = colorResource(R.color.black),
                            fontSize = 18.sp
                        )
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
                    Text(
                        text = "Min Value: ",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )
                    minValue?.let {
                        Text(
                            text = "$$it",
                            color = colorResource(R.color.black),
                            fontSize = 18.sp
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(8.dp),
                    thickness = 2.dp,
                    color = colorResource(R.color.black)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Market Cap: ",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )
                    detailCoins?.marketCap?.let {
                        Text(
                            text = it,
                            color = colorResource(R.color.black),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}