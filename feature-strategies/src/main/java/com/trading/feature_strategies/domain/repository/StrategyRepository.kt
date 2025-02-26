package com.trading.feature_strategies.domain.repository

import com.trading.core.domain.network.model.api.Pageable
import com.trading.core.domain.network.model.reponse.ApiResult
import com.trading.feature_strategies.domain.model.CompositeStrategy

interface StrategyRepository {
    suspend fun getCompositeStrategies(
        page: Int, limit: Int
    ): ApiResult<Pageable<CompositeStrategy>>
}
