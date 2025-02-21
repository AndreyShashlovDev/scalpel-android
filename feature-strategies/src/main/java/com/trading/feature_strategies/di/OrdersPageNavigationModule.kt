package com.trading.feature_strategies.di

import com.trading.feature_strategies.router.StrategiesRouter
import com.trading.feature_strategies.router.StrategiesRouterImpl
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
    fun provideOrdersPageRouter(): StrategiesRouter = StrategiesRouterImpl()
}
