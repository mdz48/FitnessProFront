package com.example.fitnessprofront.features.user.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitnessprofront.features.user.domain.usecases.UserLoginUseCase

class LoginViewModelFactory(
    private val userLoginUseCase: UserLoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userLoginUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}