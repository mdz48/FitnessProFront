package com.example.fitnessprofront.features.user.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitnessprofront.core.navigation.FeatureNavGraph
import com.example.fitnessprofront.core.navigation.Home
import com.example.fitnessprofront.core.navigation.Login
import com.example.fitnessprofront.core.navigation.Register
import com.example.fitnessprofront.features.user.di.UserModule
import com.example.fitnessprofront.features.user.presentation.screens.LoginScreen
import com.example.fitnessprofront.features.user.presentation.screens.RegisterScreen
import com.example.fitnessprofront.features.user.presentation.viewmodels.LoginViewModel
import com.example.fitnessprofront.features.user.presentation.viewmodels.RegisterViewModel

class UserNavGraph(
    private val userModule: UserModule
) : FeatureNavGraph {

    override fun registerNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable<Login> {
            val viewModel: LoginViewModel =
                viewModel(factory = userModule.provideUserViewModelFactory())

            LoginScreen(
                viewModel = viewModel,
                onClickLogin = {
                    navController.navigate(Home) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Register)
                }
            )
        }

        navGraphBuilder.composable<Register> {
            val viewModel: RegisterViewModel =
                viewModel(factory = userModule.provideRegisterViewModelFactory())

            RegisterScreen(
                viewModel = viewModel,
                onRegisterSuccess = {
                    navController.navigate(Login) {
                        popUpTo(Register) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(Register) { inclusive = true }
                    }
                }
            )
        }
    }
}
