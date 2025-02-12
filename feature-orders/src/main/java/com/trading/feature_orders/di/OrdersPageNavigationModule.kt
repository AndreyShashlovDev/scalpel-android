package com.trading.feature_orders.di

import com.trading.feature_orders.router.OrdersRouter
import com.trading.feature_orders.router.OrdersRouterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrdersPageNavigationModule {

    @Provides
    @Singleton
    fun provideOrdersPageRouter(): OrdersRouter = OrdersRouterImpl()
}
