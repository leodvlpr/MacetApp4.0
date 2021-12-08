package com.example.macetapp40.repository

import com.example.macetapp40.model.Plant
import com.example.macetapp40.model.User

interface MacetappRepository {
    suspend fun getPlantByUser(userId: String) : MacetappResult<Plant>
    suspend fun getUser(userGoogleId: String) : MacetappResult<User>
}