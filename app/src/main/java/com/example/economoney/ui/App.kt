package com.example.economoney.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.economoney.R
import com.example.economoney.ui.navigate.item.NavItem
import com.example.economoney.ui.navigate.Screens
import com.example.economoney.ui.navigate.SetUpNavHost
import com.example.economoney.ui.navigate.item.MenuItem
import com.example.economoney.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    homeViewModel: HomeViewModel,
    navHostController: NavHostController
) {
    var expanded by remember { mutableStateOf(false) }

    val backStackEntry = navHostController.currentBackStackEntryAsState().value
    val currentPage = backStackEntry?.destination?.route

    val navItemList = listOf(
        NavItem("Home", Icons.Filled.Home, router = Screens.Home.router),
        NavItem("Settings", Icons.Filled.Settings, router = Screens.Settings.router)
    )

    val menuItemList = listOf(
        MenuItem(label = "1h"),
        MenuItem(label = "3h"),
        MenuItem(label = "12h"),
        MenuItem(label = "24h"),
        MenuItem(label = "7d"),
        MenuItem(label = "30d"),
        MenuItem(label = "3m"),
        MenuItem(label = "1y"),
        MenuItem(label = "3y"),
        MenuItem(label = "5y"),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(R.color.white),
                    titleContentColor = colorResource(R.color.black),
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = colorResource(R.color.black),
                        fontSize = 32.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    if (currentPage != Screens.Home.router) {
                        IconButton(onClick = { navHostController.popBackStack() }) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Arrow Back Icon",
                                tint = colorResource(id = R.color.black)
                            )
                        }
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier.clickable {
                                expanded = !expanded
                            },
                            text = homeViewModel.time,
                            fontSize = 20.sp,
                            color = colorResource(R.color.black)
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            Column(
                                modifier = Modifier
                                    .height(200.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                menuItemList.forEach { menuItem ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = menuItem.label,
                                                fontSize = 16.sp,
                                                textAlign = TextAlign.Center,
                                                color = colorResource(R.color.black)
                                            )
                                        },
                                        onClick = {
                                            homeViewModel.time = menuItem.label
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = colorResource(id = R.color.white)) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = navItem.router == currentPage,
                        onClick = {
                            navHostController.navigate(route = navItem.router) {
                                navHostController.graph.startDestinationRoute?.let {
                                    popUpTo(it)
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                imageVector = navItem.icon,
                                contentDescription = navItem.label,
                                tint = colorResource(id = R.color.black)
                            )
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
        ) {
            SetUpNavHost(navHostController = navHostController, homeViewModel = homeViewModel)
        }
    }
}
