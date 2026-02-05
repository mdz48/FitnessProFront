package com.example.fitnessprofront.features.user.di

import com.example.fitnessprofront.core.di.AppContainer
import com.example.fitnessprofront.features.user.domain.usecases.UserLoginUseCase
import com.example.fitnessprofront.features.user.domain.usecases.UserRegisterUseCase
import com.example.fitnessprofront.features.user.presentation.viewmodels.LoginViewModelFactory
import com.example.fitnessprofront.features.user.presentation.viewmodels.RegisterViewModelFactory

class UserModule(
    private val appContainer: AppContainer
) {
    fun provideUserLoginUseCase(): UserLoginUseCase {
        return UserLoginUseCase(appContainer.userRepository)
    }

    fun provideUserRegisterUseCase(): UserRegisterUseCase {
        return UserRegisterUseCase(appContainer.userRepository)
    }

    fun provideUserViewModelFactory(): LoginViewModelFactory {
        return LoginViewModelFactory(
            provideUserLoginUseCase()
        )
    }

    fun provideRegisterViewModelFactory(): RegisterViewModelFactory {
        return RegisterViewModelFactory(
            provideUserRegisterUseCase()
        )
    }
}