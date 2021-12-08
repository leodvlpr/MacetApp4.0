package com.example.macetapp40.repository

import com.example.macetapp40.model.Plant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*

interface MacetappAPI {
    @GET("android/{userId}")
    suspend fun getPlantByUserId (@Path("userId") userId: String) : Response<Plant>

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