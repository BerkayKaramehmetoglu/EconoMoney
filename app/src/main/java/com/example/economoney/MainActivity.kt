package com.example.economoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.economoney.ui.App
import com.example.economoney.ui.theme.EconoMoneyTheme
import com.example.economoney.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EconoMoneyTheme {
                val homeViewModel: HomeViewModel = hiltViewModel()
                navHostController = rememberNavController()
                App(homeViewModel = homeViewModel, navHostController = navHostController)
            }
        }
    }
}