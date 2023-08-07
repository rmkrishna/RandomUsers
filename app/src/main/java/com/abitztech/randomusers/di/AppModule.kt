package com.abitztech.randomusers.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun getDefaultCoroutineContext(): CoroutineContext {
        return Dispatchers.Default
    }

    @Provides
    fun getContext(app: Application): Context {
        return app.applicationContext
    }
}