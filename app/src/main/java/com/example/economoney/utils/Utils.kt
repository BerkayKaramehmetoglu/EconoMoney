package com.example.economoney.utils

import android.graphics.Color as Colors
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.example.economoney.data.entity.Coins

object Utils {

    fun coinColor(coins: Coins, color: (Color) -> Unit) {
        try {
            val parsedColor = coins.color?.let { Colors.valueOf(it.toColorInt()) }
            if (parsedColor != null) {
                color(Color(parsedColor.toArgb()))
            } else {
                color(Color.Black)
            }
        } catch (e: Exception) {
            println(e)
        }
    }
}