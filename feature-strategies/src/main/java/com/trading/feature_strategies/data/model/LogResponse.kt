package com.trading.feature_strategies.data.model

import com.trading.core.domain.network.model.api.LogType
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class LogResponse(
    val id: Int, val type: LogType,
//    val log: JsonObject,
    @Contextual val createdAt: Date
)
