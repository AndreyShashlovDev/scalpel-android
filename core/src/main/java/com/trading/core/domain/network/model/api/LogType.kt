package com.trading.core.domain.network.model.api

import com.trading.core.domain.network.serializer.LogTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = LogTypeSerializer::class)
sealed class LogType(open val value: String) {
    data object Swap : LogType("SWAP")

    data class Undefined(override val value: String) : LogType(value)
}
