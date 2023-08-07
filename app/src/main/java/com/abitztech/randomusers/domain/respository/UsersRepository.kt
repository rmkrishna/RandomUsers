package com.abitztech.randomusers.domain.respository

import com.abitztech.randomusers.data.model.Result
import com.abitztech.randomusers.data.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getRandomUser(): Flow<Result<List<User>>>
    suspend fun getUser(id: String): User?
}