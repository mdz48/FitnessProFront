package com.example.fitnessprofront.core.network

import com.example.fitnessprofront.features.recipies.data.datasources.remote.model.RecipeCreateDto
import com.example.fitnessprofront.features.recipies.data.datasources.remote.model.RecipeDto
import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserCreateDto
import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserDto
import com.example.fitnessprofront.features.user.data.datasources.remote.model.UserLoginResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface FitnessProApi {
    @GET("recipes")
    suspend fun getRecipies(): List<RecipeDto>

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

    @POST("users")
    suspend fun register(
        @Body user: UserCreateDto
    ): UserDto

    @POST("recipes")
    suspend fun createRecipe(
        @Body recipe: RecipeCreateDto
    ): RecipeDto

    @PUT("recipes/{recipe_id}")
    suspend fun updateRecipe(
        @Path("recipe_id") recipeId: Int,
        @Body recipe: RecipeCreateDto
    ): RecipeDto

    @DELETE("recipes/{recipe_id}")
    suspend fun deleteRecipe(
        @Path("recipe_id") recipeId: Int
    )
}