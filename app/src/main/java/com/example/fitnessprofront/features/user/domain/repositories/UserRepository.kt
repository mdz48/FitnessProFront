package com.example.fitnessprofront.features.user.domain.repositories

import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserDto
import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserLoginResponseDto
import com.example.fitnessprofront.features.user.domain.entities.User

interface UserRepository {
    suspend fun isUserLoggedIn(): Boolean
    suspend fun getUser(id: Int): User
    suspend fun login(email: String, password: String): UserLoginResponseDto
    suspend fun register(name: String, email: String, password: String): User
}