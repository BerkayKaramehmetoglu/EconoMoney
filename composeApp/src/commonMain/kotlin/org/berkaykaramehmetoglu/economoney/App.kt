package org.berkaykaramehmetoglu.economoney

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.berkaykaramehmetoglu.economoney.ui.Screens
import org.berkaykaramehmetoglu.economoney.ui.SetUpNavHost

lateinit var navController: NavHostController

@Composable
fun App() {
    val selectedItem = remember { mutableStateOf(0) }
    navController = rememberNavController()

    LaunchedEffect(selectedItem.value) {
        when (selectedItem.value) {
            0 -> navController.navigate(Screens.HomePage.router) {
                launchSingleTop = true
            }

            1 -> navController.navigate(Screens.NewsPage.router) {
                launchSingleTop = true
            }

            2 -> navController.navigate(Screens.SettingsPage.router) {
                launchSingleTop = true
            }
        }
    }

    MaterialTheme {
        Scaffold(bottomBar = {
            BottomAppBar(content = {
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = { selectedItem.value = 0 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = { Text(text = "Home") }
                )
                NavigationBarItem(
                    selected = selectedItem.value == 1,
                    onClick = { selectedItem.value = 1 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Newspaper,
                            contentDescription = "News"
                        )
                    },
                    label = { Text(text = "News") }
                )
                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = { selectedItem.value = 2 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    },
                    label = { Text(text = "Settings") }
                )
            })
        }) { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                SetUpNavHost(navController)
            }
        }
    }
}