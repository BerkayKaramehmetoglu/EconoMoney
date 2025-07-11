package com.example.economoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.economoney.ui.App
import com.example.economoney.ui.navigate.Screens
import com.example.economoney.ui.theme.EconoMoneyTheme

class MainActivity : ComponentActivity() {
    lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EconoMoneyTheme {
                navHostController = rememberNavController()
                App(navHostController = navHostController)
            }
        }
    }
}