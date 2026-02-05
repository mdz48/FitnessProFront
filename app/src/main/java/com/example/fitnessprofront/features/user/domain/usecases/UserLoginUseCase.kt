package com.example.fitnessprofront.features.user.domain.usecases

import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserLoginResponseDto
import com.example.fitnessprofront.features.user.domain.repositories.UserRepository

class UserLoginUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): UserLoginResponseDto {
        return userRepository.login(email, password)
    }
}