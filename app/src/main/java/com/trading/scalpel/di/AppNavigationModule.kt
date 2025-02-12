package com.trading.scalpel.di

import com.trading.core.router.AppNavigationRegistry
import com.trading.core.view.walletconnect.WalletConnectRegistry
import com.trading.feature_login.router.LoginRouterRegistry
import com.trading.feature_orders.router.OrdersRouterRegistry
import com.trading.scalpel.router.SplashRouterRegistry
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
    fun provideSplashNavigationRegistry(
        registry: SplashRouterRegistry,
    ): AppNavigationRegistry = registry

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

    @Provides
    @IntoSet
    @JvmSuppressWildcards
    fun provideOrdersNavigationRegistry(
        registry: OrdersRouterRegistry,
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
