package com.trading.feature_login.di

import com.trading.feature_login.domain.router.LoginRouter
import com.trading.feature_login.domain.router.LoginRouterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginNavigationModule {

    @Provides
    @Singleton
    fun provideLoginRouter(): LoginRouter = LoginRouterImpl()
}
