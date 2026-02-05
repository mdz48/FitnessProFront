package com.example.fitnessprofront.features.user.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitnessprofront.core.navigation.FeatureNavGraph
import com.example.fitnessprofront.core.navigation.Login
import com.example.fitnessprofront.features.user.di.UserModule
import com.example.fitnessprofront.features.user.presentation.screens.LoginScreen
import com.example.fitnessprofront.features.user.presentation.viewmodels.LoginViewModel

class UserNavGraph (
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
                onClickLogin = { navController.navigateUp() }
            )
        }
    }
}