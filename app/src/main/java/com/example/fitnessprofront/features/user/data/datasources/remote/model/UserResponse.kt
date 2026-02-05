package com.example.fitnessprofront.features.user.data.datasources.remote.model

data class UsersResponse (
    val data: List<UserDto>
)

data class UserDto (
    val id : Int,
    val email : String,
    val lastname : String,
)

data class UserLoginResponseDto (
    val access_token : String,
    val token_type : String,
    val user : UserDto
)