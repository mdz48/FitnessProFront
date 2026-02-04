package com.example.fitnessprofront.core.network

import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserDto
import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserLoginResponseDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FitnessProApi {
    @GET("recipies")
    suspend fun getRecipies()

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): UserLoginResponseDto

    @GET("users")
    suspend fun getUser(
        @Query("id") id : Int
    ): UserDto

    @FormUrlEncoded
    @POST("users")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): UserDto
}