package com.trading.core.services.auth.di

import com.trading.core.services.auth.provider.BasicTokenProviderImpl
import com.trading.core.services.auth.provider.TokenProvider
import com.trading.core.services.auth.provider.TokenProviderNotifier
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthProviderModule {

    @Binds
    @Singleton
    abstract fun bindTokenProvider(impl: BasicTokenProviderImpl): TokenProvider

    @Binds
    @Singleton
    abstract fun bindTokenProviderNotifier(impl: BasicTokenProviderImpl): TokenProviderNotifier
}
