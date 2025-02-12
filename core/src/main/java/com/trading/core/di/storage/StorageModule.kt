package com.trading.core.di.storage

import android.app.Application
import com.trading.core.data.local.SecureStorageImpl
import com.trading.core.domain.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideStorage(app: Application): Storage = SecureStorageImpl(app.applicationContext)
}
