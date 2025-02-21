package com.trading.feature_strategies.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StrategyOptionsResponse(
    val stopLoss: Float? = null,
)
