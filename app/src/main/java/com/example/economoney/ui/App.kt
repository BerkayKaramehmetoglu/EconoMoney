package com.example.economoney.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.economoney.R
import com.example.economoney.ui.navigate.NavItem
import com.example.economoney.ui.navigate.Screens
import com.example.economoney.ui.navigate.SetUpNavHost
import com.example.economoney.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navHostController: NavHostController) {
    val backStackEntry = navHostController.currentBackStackEntryAsState().value
    val currentPage = backStackEntry?.destination?.route

    val navItemList = listOf(
        NavItem("Home", Icons.Filled.Home, router = Screens.Home.router),
        NavItem("Settings", Icons.Filled.Settings, router = Screens.Settings.router)
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
                    Utils.ActionIcon(currentPage)
                },
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
        Column(modifier = Modifier.padding(innerPadding)) {
            SetUpNavHost(navHostController = navHostController)
        }
    }
}
