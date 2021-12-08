package com.example.macetapp40.repository

import com.example.macetapp40.model.Plant
import com.example.macetapp40.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*

interface MacetappAPI {
    @GET("users/{userId}/plants")
    suspend fun getPlantByUserId (@Path("userId") userId: String) : Response<Plant>

    @GET("users/{userGoogleId}")
    suspend fun getUser (@Path("userGoogleId") userGoogleId: String) : Response<User>

    @POST("users")
    suspend fun createUser (
    @Field("googleId") userGoogleId: String
    )

    companion object {
        fun provideMacetappAPI(retrofit: Retrofit) : MacetappAPI {
            return retrofit.create(MacetappAPI::class.java)
        }
    }
}