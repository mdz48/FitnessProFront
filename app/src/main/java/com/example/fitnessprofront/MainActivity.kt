package com.example.fitnessprofront

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessprofront.core.di.AppContainer
import com.example.fitnessprofront.core.navigation.NavigationWrapper
import com.example.fitnessprofront.core.theme.FitnessProFrontTheme
import com.example.fitnessprofront.features.recipies.di.RecipeModule
import com.example.fitnessprofront.features.recipies.navigation.RecipeNavGraph
import com.example.fitnessprofront.features.user.di.UserModule
import com.example.fitnessprofront.features.user.navigation.UserNavGraph
import com.example.fitnessprofront.features.user.presentation.screens.LoginScreen

class MainActivity : ComponentActivity() {
    lateinit var appContainer: AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = AppContainer(context = this)
        val userModule = UserModule(appContainer)
        val recipeModule = RecipeModule(appContainer)

        val navGraph = listOf(
            UserNavGraph(userModule),
            RecipeNavGraph(recipeModule)
        )
        enableEdgeToEdge()
        setContent {
            FitnessProFrontTheme {
//                LoginScreen(userModule.provideUserViewModelFactory())
                NavigationWrapper(navGraph)
            }

        }
    }
}