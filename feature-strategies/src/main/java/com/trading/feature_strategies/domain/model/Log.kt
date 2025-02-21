package com.trading.feature_strategies.domain.model

import com.trading.core.domain.network.model.api.LogType
import java.util.Date

data class Log(
    val id: Int, val type: LogType,
//    val log: JsonObject,
    val createdAt: Date
)
