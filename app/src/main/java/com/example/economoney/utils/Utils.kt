package com.example.economoney.utils

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.economoney.R
import com.example.economoney.ui.navigate.Screens

object Utils {

    @Composable
    fun ActionIcon(currentPage: String?) {
        when (currentPage) {
            Screens.Home.router -> Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Filled.Home,
                contentDescription = "Home Icon",
                tint = colorResource(id = R.color.black)
            )

            Screens.Settings.router ->
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings Icon",
                    tint = colorResource(id = R.color.black)
                )
        }
    }
}