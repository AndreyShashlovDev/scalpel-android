package com.trading.feature_login.di

import com.trading.feature_login.router.LoginRouter
import com.trading.feature_login.router.LoginRouterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginPageNavigationModule {

    @Provides
    @Singleton
    fun provideLoginPageRouter(): LoginRouter = LoginRouterImpl()
}
