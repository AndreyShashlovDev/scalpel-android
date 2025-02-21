package com.trading.feature_strategies.di

import com.trading.feature_strategies.data.api.strategy.StrategyApiService
import com.trading.feature_strategies.data.repository.strategy.StrategyRepositoryImpl
import com.trading.feature_strategies.domain.repository.StrategyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StrategyRepositoryModule {

    @Provides
    @Singleton
    fun provideStrategyApiService(retrofit: Retrofit): StrategyApiService {
        return retrofit.create(StrategyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideStrategyRepository(impl: StrategyRepositoryImpl): StrategyRepository = impl
}
