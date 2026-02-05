package com.example.fitnessprofront.features.recipies.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.fitnessprofront.core.navigation.AddRecipe
import com.example.fitnessprofront.core.navigation.FeatureNavGraph
import com.example.fitnessprofront.core.navigation.Home
import com.example.fitnessprofront.features.recipies.di.RecipeModule
import com.example.fitnessprofront.features.recipies.presentation.HomeScreen
import com.example.fitnessprofront.features.recipies.presentation.screens.AddRecipeScreen

class RecipeNavGraph (
    private val recipeModule: RecipeModule
) : FeatureNavGraph {
    override fun registerNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable<Home> {
            HomeScreen(
                recipiesViewModelFactory = recipeModule.provideRecipiesViewModelFactory(),
                onNavigateToAddRecipe = {
                    navController.navigate(AddRecipe)
                }
            )
        }

        navGraphBuilder.composable<AddRecipe> {
            AddRecipeScreen(
                factory = recipeModule.provideRecipiesViewModelFactory(),
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

