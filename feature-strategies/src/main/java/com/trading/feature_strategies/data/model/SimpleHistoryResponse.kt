package com.trading.feature_strategies.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SimpleHistoryResponse(
    val date: Long,
    val value: String
)