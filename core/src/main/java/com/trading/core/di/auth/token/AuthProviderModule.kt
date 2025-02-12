package com.trading.core.di.auth.token

import com.trading.core.domain.auth.BasicTokenProviderImpl
import com.trading.core.domain.auth.TokenProvider
import com.trading.core.domain.auth.TokenProviderNotifier
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
