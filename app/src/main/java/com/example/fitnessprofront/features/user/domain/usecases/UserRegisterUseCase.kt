package com.example.fitnessprofront.features.user.domain.usecases

import com.example.fitnessprofront.features.user.domain.entities.User
import com.example.fitnessprofront.features.user.domain.repositories.UserRepository

class UserRegisterUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        name: String,
        lastname: String,
        password: String
    ): User {
        return userRepository.register(email, name, lastname, password)
    }
}
