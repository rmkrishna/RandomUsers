package com.abitztech.randomusers.di

import com.abitztech.randomusers.data.repository.UserRepositoryImpl
import com.abitztech.randomusers.domain.respository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        usersRepositoryImpl: UserRepositoryImpl
    ): UsersRepository
}