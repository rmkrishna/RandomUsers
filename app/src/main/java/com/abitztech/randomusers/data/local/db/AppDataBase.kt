package com.abitztech.randomusers.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abitztech.randomusers.data.local.db.dao.UserDao
import com.abitztech.randomusers.data.local.db.entity.UserEntity

const val DB_VERSION_V1 = 1

@Database(
    entities = [UserEntity::class], version = DB_VERSION_V1, exportSchema = false
)
abstract class DB : RoomDatabase() {
    abstract fun userDao(): UserDao
}