package com.example.macetapp40.repository

import com.example.macetapp40.model.Plant
import com.example.macetapp40.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MacetappRepositoryImpl(private val macetappAPI: MacetappAPI) : MacetappRepository {
    override suspend fun getPlantByUser(userId: String): MacetappResult<Plant> {
       return withContext(Dispatchers.IO) {
            executeRetrofitRequest { macetappAPI.getPlantByUserId(userId) }
        }
    }

    override suspend fun getUser(userGoogleId: String): MacetappResult<User> {
        return withContext(Dispatchers.IO) {
            executeRetrofitRequest { macetappAPI.getUser(userGoogleId) }
        }
    }
}