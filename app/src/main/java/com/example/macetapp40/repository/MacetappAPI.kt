package com.example.macetapp40.repository

import com.example.macetapp40.model.Plant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface MacetappAPI {
    @GET("users/{userId}/plants")
    suspend fun getPlantByUserId (@Path("userId") userId: String) : Response<Plant>
    companion object {
        fun provideMacetappAPI(retrofit: Retrofit) : MacetappAPI {
            return retrofit.create(MacetappAPI::class.java)
        }
    }
}