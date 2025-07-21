package com.example.economoney.ui.charts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
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
        paddingRight = 0.dp,
        bottomPadding = 0.dp,
        paddingTop = 16.dp,
        containerPaddingEnd = 0.dp,
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
    )

    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )
}

@Composable
fun LineCharts(
    coins: Coins,
    modifier: Modifier,
    color: Color,
    Ymin: (Float?) -> Unit,
    Ymax: (Float?) -> Unit
) {

    val sparklineValues = coins.sparkline
        ?.filterNotNull()
        ?.mapIndexed { index, value ->
            Point(index.toFloat(), value.toFloat())
        } ?: emptyList()

    val minY = sparklineValues.minByOrNull { it.y }?.y
    val maxY = sparklineValues.maxByOrNull { it.y }?.y

    Ymin(minY)
    Ymax(maxY)

    val yAxisData = AxisData.Builder()
        .backgroundColor(color)
        .axisLineColor(colorResource(R.color.black))
        .axisLabelColor(colorResource(R.color.black))
        .steps(8)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val maxY = sparklineValues.maxOfOrNull { it.y } ?: 1f
            val stepValue = maxY / 5
            ((i * stepValue).toInt()).toString()
        }.build()

    val lineChartData = LineChartData(
        paddingRight = 0.dp,
        bottomPadding = 0.dp,
        paddingTop = 32.dp,
        containerPaddingEnd = 0.dp,
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = sparklineValues,
                    lineStyle = LineStyle(color = colorResource(R.color.black)),
                    intersectionPoint = IntersectionPoint(),
                    selectionHighlightPoint = SelectionHighlightPoint(color = colorResource(R.color.black)),
                    shadowUnderLine = ShadowUnderLine(),
                    selectionHighlightPopUp = SelectionHighlightPopUp()
                )
            )
        ),
        yAxisData = yAxisData,
        gridLines = GridLines(color = colorResource(R.color.black)),
        backgroundColor = color
    )

    LineChart(
        modifier = modifier,
        lineChartData = lineChartData,
    )
}