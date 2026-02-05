package com.example.fitnessprofront.features.user.data.datasources.remote.mapper

import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserDto
import com.example.fitnessprofront.features.user.domain.entities.User

fun UserDto.toDomain(): User {
    return User(
        id = this.id,
        email = this.email,
        name = this.name,
        lastname = this.lastname
    )
}