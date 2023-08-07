package com.abitztech.randomusers.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.abitztech.randomusers.data.local.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM users WHERE id = :id")
    fun getEntity(id: String): UserEntity?

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>
}