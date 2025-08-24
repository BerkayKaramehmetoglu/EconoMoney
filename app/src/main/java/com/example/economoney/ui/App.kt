package com.example.economoney.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
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
import com.example.economoney.viewmodels.DetailViewModel
import com.example.economoney.viewmodels.HomeViewModel
import com.example.economoney.viewmodels.TrendViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    homeViewModel: HomeViewModel,
    trendViewModel: TrendViewModel,
    navHostController: NavHostController,
    detailViewModel: DetailViewModel
) {
    val time by homeViewModel.time.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var expandedOrder by remember { mutableStateOf(false) }

    val radioOptionsDirection = listOf("Decreasing Order" to "desc", "Ascending Order" to "asc")
    val (selectedOptionDirection, onOptionSelectedDirection) = remember {
        mutableStateOf(radioOptionsDirection[0])
    }

    val radioOptionsBy = listOf(
        "Price" to "price",
        "Market Cap" to "marketCap",
        "Hours Volume" to "24hVolume",
        "Change" to "change"
    )
    val (selectedOptionBy, onOptionSelectedBy) = remember { mutableStateOf(radioOptionsBy[1]) }

    val backStackEntry = navHostController.currentBackStackEntryAsState().value
    val currentPage = backStackEntry?.destination?.route

    val navItemList = listOf(
        NavItem(label = "Home", Icons.Filled.Home, router = Screens.Home.router),
        NavItem(label = "Trend", Icons.Filled.Star, router = Screens.Trend.router)
    )
    val menuItemList = listOf(
        MenuItem(label = "1 Hours" to "1h"),
        MenuItem(label = "3 Hours" to "3h"),
        MenuItem(label = "12 Hours" to "12h"),
        MenuItem(label = "24 Hours" to "24h"),
        MenuItem(label = "7 Day" to "7d"),
        MenuItem(label = "30 Day" to "30d"),
        MenuItem(label = "3 Month" to "3m"),
        MenuItem(label = "1 Year" to "1y"),
        MenuItem(label = "3 Year" to "3y"),
        MenuItem(label = "5 Year" to "5y"),
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
                    } else {
                        Box(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        expandedOrder = !expandedOrder
                                    },
                                imageVector = Icons.Filled.Build,
                                contentDescription = "Build Icon",
                                tint = colorResource(id = R.color.black)
                            )
                            DropdownMenu(
                                expanded = expandedOrder,
                                onDismissRequest = { expandedOrder = false }
                            ) {
                                DropdownMenuItem(
                                    text = {
                                        Column(
                                            Modifier
                                                .selectableGroup()
                                                .fillMaxHeight(),
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(bottom = 8.dp),
                                                text = "Order Direction",
                                                fontSize = 16.sp,
                                                textAlign = TextAlign.Center
                                            )
                                            radioOptionsDirection.forEach { radio ->
                                                Row(
                                                    Modifier
                                                        .selectable(
                                                            selected = (radio == selectedOptionDirection),
                                                            onClick = {
                                                                onOptionSelectedDirection(radio)
                                                                homeViewModel.setOrderDirection(
                                                                    radio.second
                                                                )
                                                            },
                                                            role = Role.RadioButton
                                                        ),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    RadioButton(
                                                        selected = (radio == selectedOptionDirection),
                                                        onClick = {
                                                            onOptionSelectedDirection(radio)
                                                            homeViewModel.setOrderDirection(radio.second)
                                                        }
                                                    )
                                                    Text(text = radio.first)
                                                }
                                            }
                                        }
                                    },
                                    onClick = {/* Don't use anymore */ }
                                )
                                Spacer(modifier = Modifier.size(24.dp))
                                DropdownMenuItem(
                                    text = {
                                        Column(
                                            Modifier
                                                .selectableGroup()
                                                .fillMaxSize(),
                                        ) {
                                            Text(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(bottom = 8.dp),
                                                text = "Order By",
                                                fontSize = 16.sp,
                                                textAlign = TextAlign.Center
                                            )
                                            radioOptionsBy.forEach { radio ->
                                                Row(
                                                    Modifier
                                                        .selectable(
                                                            selected = (radio == selectedOptionBy),
                                                            onClick = {
                                                                onOptionSelectedBy(radio)
                                                                homeViewModel.setOrderBy(radio.second)
                                                            },
                                                            role = Role.RadioButton
                                                        ),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    RadioButton(
                                                        selected = (radio == selectedOptionBy),
                                                        onClick = {
                                                            onOptionSelectedBy(radio)
                                                            homeViewModel.setOrderBy(radio.second)
                                                        }
                                                    )
                                                    Text(text = radio.first)
                                                }
                                            }
                                        }
                                    },
                                    onClick = {/* Don't use anymore*/ }
                                )
                            }
                        }
                    }
                },
                actions = {
                    if (currentPage == Screens.Home.router) {
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                modifier = Modifier.clickable {
                                    expanded = !expanded
                                },
                                text = time,
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
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 8.dp),
                                        text = "Time",
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Center
                                    )
                                    menuItemList.forEach { menuItem ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    text = menuItem.label.first,
                                                    fontSize = 16.sp,
                                                    textAlign = TextAlign.Center,
                                                    color = colorResource(R.color.black)
                                                )
                                            },
                                            onClick = {
                                                homeViewModel.setTime(menuItem.label.second)
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = time,
                            fontSize = 20.sp,
                            color = colorResource(R.color.black)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = colorResource(R.color.white)) {
                navItemList.forEach { navItem ->
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
                                tint = colorResource(R.color.black)
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
                .background(color = colorResource(R.color.white))
        ) {
            SetUpNavHost(
                navHostController = navHostController,
                homeViewModel = homeViewModel,
                trendViewModel = trendViewModel,
                detailViewModel = detailViewModel
            )
        }
    }
}