package com.trading.core.domain.network.model.api

import com.trading.core.domain.network.serializer.ChainTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ChainTypeSerializer::class)
sealed class ChainType(open val value: String, val id: Int, val name: String) {
    data object Ethereum : ChainType("ETHEREUM_MAIN_NET", 1, "ethereum")
    data object Polygon : ChainType("POLYGON", 137, "polygon")
    data class Undefined(override val value: String) : ChainType(value, 0, "unknown")
}
