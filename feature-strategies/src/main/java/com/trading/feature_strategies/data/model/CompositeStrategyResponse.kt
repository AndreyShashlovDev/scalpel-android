package com.trading.feature_strategies.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CompositeStrategyResponse(
    val strategy: StrategyResponse,
    val swaps: List<SwapResponse>,
    val latestLog: LogResponse? = null,
    val swapHistory: List<SimpleHistoryResponse>
)
