package com.example.macetapp40.repository

import com.example.macetapp40.model.Plant

interface MacetappRepository {
    suspend fun getPlantByUser(userId: String) : MacetappResult<Plant>
}