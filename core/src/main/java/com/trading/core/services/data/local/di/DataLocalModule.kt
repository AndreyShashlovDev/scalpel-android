package com.trading.core.services.data.local.di

import android.app.Application
import com.trading.core.services.data.local.SecureStorageImpl
import com.trading.core.services.data.local.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataLocalModule {

    @Provides
    @Singleton
    fun provideStorage(app: Application): Storage = SecureStorageImpl(app.applicationContext)
}
