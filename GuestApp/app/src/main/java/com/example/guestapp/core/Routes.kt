package com.example.guestapp.core

import RegisterScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guestapp.ui.screens.GuestListScreen
import com.example.guestapp.ui.screens.LoginScreen
import com.example.guestapp.ui.screens.ProfileScreen




@Composable
fun Routes() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "LoginScreen"
    ) {
        composable("GuestListScreen") {
            GuestListScreen(navController)
        }
        composable("ProfileScreen") {
            ProfileScreen(navController)
        }
        composable("LoginScreen") {
            LoginScreen(navController)
        }
        composable("RegisterScreen") {
            RegisterScreen(navController)
        }
    }
}
