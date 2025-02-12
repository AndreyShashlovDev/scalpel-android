package com.trading.scalpel.di

import com.trading.scalpel.router.SplashRouter
import com.trading.scalpel.router.SplashRouterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SplashPageNavigationModule {

    @Provides
    @Singleton
    fun provideSplashPageRouter(): SplashRouter = SplashRouterImpl()
}
