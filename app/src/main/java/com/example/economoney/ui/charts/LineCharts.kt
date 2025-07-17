package com.example.economoney.ui.charts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import com.example.economoney.R
import com.example.economoney.data.entity.Coins
import com.example.economoney.utils.Utils

@Composable
fun LineCharts(coins: Coins, modifier: Modifier) {
    var color = Color.Black
    Utils.coinColor(coins) { colors -> color = colors }

    val sparklineValues = coins.sparkline
        ?.filterNotNull()
        ?.mapIndexed { index, value ->
            Point(index.toFloat(), value.toFloat())
        } ?: emptyList()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = sparklineValues,
                    lineStyle = LineStyle(color = color),
                )
            )
        ),
        backgroundColor = colorResource(R.color.light_gray),
        isZoomAllowed = false,
        paddingRight = 0.dp,
    )

    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )
}
