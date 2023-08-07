package com.abitztech.randomusers.di

import android.content.Context
import androidx.room.Room
import com.abitztech.randomusers.data.local.db.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "dental_admin.db"

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {
    @Singleton
    @Provides
    fun getDB(context: Context): DB {
        return Room.databaseBuilder(context, DB::class.java, DB_NAME).build()
    }

    @Provides
    fun getUserDao(db: DB) = db.userDao()
}