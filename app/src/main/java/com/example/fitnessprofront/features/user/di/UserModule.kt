package com.example.fitnessprofront.features.user.di

import com.example.fitnessprofront.core.di.AppContainer
import com.example.fitnessprofront.features.user.domain.usecases.UserLoginUseCase
import com.example.fitnessprofront.features.user.presentation.viewmodels.LoginViewModelFactory

class UserModule (
    private val appContainer: AppContainer
) {
    fun provideUserLoginUseCase(): UserLoginUseCase {
        return UserLoginUseCase(appContainer.userRepository)
    }

    fun provideUserViewModelFactory(): LoginViewModelFactory {
        return LoginViewModelFactory(
            provideUserLoginUseCase()
        )
    }
}