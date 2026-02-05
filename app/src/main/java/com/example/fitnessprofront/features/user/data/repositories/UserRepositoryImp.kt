package com.example.fitnessprofront.features.user.data.repositories

import com.example.fitnessprofront.core.network.FitnessProApi
import com.example.fitnessprofront.features.user.data.datasources.remote.mapper.toDomain
import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserCreateDto
import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserLoginResponseDto
import com.example.fitnessprofront.features.user.domain.entities.User
import com.example.fitnessprofront.features.user.domain.repositories.UserRepository

class UserRepositoryImp(
    private val fitnessProApi: FitnessProApi
): UserRepository {

    override suspend fun register(
        email: String,
        name: String,
        lastname: String,
        password: String
    ): User {
        val userCreateDto = UserCreateDto(
            email = email,
            name = name,
            lastname = lastname,
            password = password
        )

        val userDto = fitnessProApi.register(userCreateDto)
        return userDto.toDomain()
    }

    override suspend fun login(
        email: String,
        password: String
    ): UserLoginResponseDto {
        return fitnessProApi.login(
            email = email,
            password = password
        )
    }

    override suspend fun getUser(id: Int): User {
        val userDto = fitnessProApi.getUser(id)
        return userDto.toDomain()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        // TODO: Implementar lógica para verificar si el usuario está logueado
        // Por ejemplo, verificar si existe un access_token guardado en SharedPreferences
        return false
    }
}


