package com.trading.feature_strategies.presentation.model

import java.math.BigDecimal

data class StrategyOptionsUiModel(
    val stopLoss: BigDecimal? = null,
    val gasPriceLimit: Int,
    val growDiffPercentsUp: BigDecimal? = null,
    val growDiffPercentsDown: BigDecimal? = null,
    val buyMaxPrice: BigDecimal? = null,
)
