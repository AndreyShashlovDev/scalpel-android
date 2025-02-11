package com.trading.scalpel.di

import com.trading.core.router.AppNavigationRegistry
import com.trading.core.services.walletconnect.presentation.WalletConnectRegistry
import com.trading.feature_login.domain.router.LoginRouterRegistry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppNavigationModule {

    @Provides
    @IntoSet
    @JvmSuppressWildcards
    fun provideLoginNavigationRegistry(
        registry: LoginRouterRegistry,
    ): AppNavigationRegistry = registry


    @Singleton
    @Provides
    @IntoSet
    @JvmSuppressWildcards
    fun provideWalletConnectRegistry(
        registry: WalletConnectRegistry,
    ): AppNavigationRegistry = registry

//    @Provides
//    @IntoSet
//    fun provideFeedNavigationRegistry(
//        registry: FeedNavigationRegistry
//    ): AppNavigationRegistry = registry

//    @Provides
//    @IntoSet
//    fun provideProfileNavigationRegistry(
//        registry: ProfileNavigationRegistry
//    ): AppNavigationRegistry = registry
}
