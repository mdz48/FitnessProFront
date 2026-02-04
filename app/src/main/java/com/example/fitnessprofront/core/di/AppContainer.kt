package com.example.fitnessprofront.core.di

import android.content.Context
import com.example.fitnessprofront.BuildConfig
import com.example.fitnessprofront.core.network.FitnessProApi
import com.example.fitnessprofront.features.user.data.repositories.UserRepositoryImp
import com.example.fitnessprofront.features.user.domain.repositories.UserRepository
import com.example.fitnessprofront.features.user.domain.usecases.UserLoginUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val fitnessProApi: FitnessProApi by lazy {
            retrofit.create(FitnessProApi::class.java)
    }

    val userRepository : UserRepository by lazy {
         UserRepositoryImp(fitnessProApi)
    }

    val userLoginUseCase: UserLoginUseCase by lazy {
        UserLoginUseCase(userRepository)
    }
}