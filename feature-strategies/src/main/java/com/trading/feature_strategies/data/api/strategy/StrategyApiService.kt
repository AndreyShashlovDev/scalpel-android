package com.trading.feature_strategies.data.api.strategy

import com.trading.core.domain.network.model.api.Pageable
import com.trading.core.domain.network.model.reponse.ApiResponse
import com.trading.feature_strategies.data.model.CompositeStrategyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StrategyApiService {

    @GET("strategy/composite")
    suspend fun getCompositeStrategies(
        @Query("page") page: Int, @Query("limit") limit: Int
    ): ApiResponse<Pageable<CompositeStrategyResponse>>
}
