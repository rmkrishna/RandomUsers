package com.abitztech.randomusers.data.repository

import com.abitztech.randomusers.data.local.db.dao.UserDao
import com.abitztech.randomusers.data.local.db.entity.UserEntity
import com.abitztech.randomusers.data.local.db.entity.getModel
import com.abitztech.randomusers.data.local.db.entity.getModels
import com.abitztech.randomusers.data.model.Result
import com.abitztech.randomusers.data.model.User
import com.abitztech.randomusers.data.remote.UserService
import com.abitztech.randomusers.data.remote.model.response.UserResponse
import com.abitztech.randomusers.domain.respository.UsersRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl @Inject constructor(
    private val api: UserService, private val userDao: UserDao
) : UsersRepository {

    override suspend fun getUser(id: String): User? = userDao.getEntity(id)?.getModel()

    override suspend fun getRandomUser(): Flow<Result<List<User>>> = flow {
        val cachedUsers = userDao.getAll()
        if (cachedUsers.isNotEmpty()) {
            emit(Result.Success(cachedUsers.getModels()))
            return@flow
        }

        emit(Result.Loading)

        try {
            val request = api.getUsers(25, 1)
            val response = request.execute()
            if (response.isSuccessful) {
                updateUserResponse(response.body()?.results)
                emit(Result.Success(userDao.getAll().getModels()))
            } else {
                emit(Result.Error(Exception("Internal Error")))
            }
        } catch (e: Exception) { // We can handle the specific exception here
            e.printStackTrace()
            emit(Result.Error(e))
        }
    }

    private fun updateUserResponse(usersResponses: List<UserResponse>?) {
        if (usersResponses == null) return
        val userEntities = arrayListOf<UserEntity>()
        usersResponses.forEach {
            userEntities.add(
                UserEntity(
                    "${it.id.name}-${it.id.value}",
                    it.name.first,
                    it.name.last,
                    it.email,
                    it.phone,
                    it.cell,
                    it.picture.large,
                    it.picture.thumbnail
                )
            )
        }
        userDao.insert(userEntities)
    }
}