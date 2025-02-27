package com.trading.feature_strategies.domain.model

import java.math.BigInteger

data class StrategyOptions(
    val stopLossPercents: Float? = null,
    val growDiffPercentsUp: Float? = null,
    val growDiffPercentsDown: Float? = null,
    val buyMaxPrice: BigInteger? = null,
)
