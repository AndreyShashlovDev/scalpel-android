package com.trading.feature_strategies.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StrategyOptionsResponse(
    val stopLossPercents: Float? = null,
    val growDiffPercentsUp: Float? = null,
    val growDiffPercentsDown: Float? = null,
    val buyMaxPrice: String? = null,
)
