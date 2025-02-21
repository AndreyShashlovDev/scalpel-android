package com.trading.feature_strategies.domain.model

data class CompositeStrategy(
    val strategy: Strategy,
    val swaps: List<Swap>,
    val latestLog: Log? = null,
    val swapHistory: List<SimpleHistory>
)
