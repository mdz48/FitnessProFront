package com.example.fitnessprofront.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessprofront.core.di.AppContainer
import com.example.fitnessprofront.features.recipies.presentation.HomeScreen
import com.example.fitnessprofront.features.user.di.UserModule
import com.example.fitnessprofront.features.user.presentation.screens.LoginScreen

@Composable
fun NavigationWrapper(appContainer: AppContainer) {
    val userModule = UserModule(appContainer)
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                factory = userModule.provideUserViewModelFactory(),
                navController = { navController.navigate(Home) }
            )
        }

        composable <Home> {
            HomeScreen()
        }
    }
}