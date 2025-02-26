package com.trading.feature_strategies.data.repository.strategy

import com.trading.core.data.network.safeApiCall
import com.trading.core.domain.network.model.api.Pageable
import com.trading.core.domain.network.model.error.apiErrorResolver
import com.trading.core.domain.network.model.reponse.ApiResult
import com.trading.feature_strategies.data.api.strategy.StrategyApiService
import com.trading.feature_strategies.data.mapper.CompositeStrategyMapper.toDomain
import com.trading.feature_strategies.domain.model.CompositeStrategy
import com.trading.feature_strategies.domain.repository.StrategyRepository
import javax.inject.Inject

class StrategyRepositoryImpl @Inject constructor(
    private val apiService: StrategyApiService
) : StrategyRepository {

    override suspend fun getCompositeStrategies(
        page: Int, limit: Int
    ): ApiResult<Pageable<CompositeStrategy>> {
        return safeApiCall({ apiService.getCompositeStrategies(page, limit) },
                           errorResolver = { apiErrorResolver(it) },
                           transform = { response ->
                               val pageable = response.data

                               if (pageable != null) {
                                   Pageable(
                                       pageable.data.map { strategy -> strategy.toDomain() }, 0, 0
                                   )
                               } else {
                                   Pageable(emptyList(), 0, 0)
                               }
                           })
    }
}
