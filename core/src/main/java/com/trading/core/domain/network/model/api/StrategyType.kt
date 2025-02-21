package com.trading.core.domain.network.model.api

import com.trading.core.domain.network.serializer.StrategyTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StrategyTypeSerializer::class)
sealed class StrategyType(open val value: String) {
    data object ClassicScalpel : StrategyType("CLASSIC_SCALPEL")
    data object ClassicScalpelTest : StrategyType("CLASSIC_SCALPEL_TEST")
    data object ClassicScalpelTestV2 : StrategyType("CLASSIC_SCALPEL_TEST_V2")

    data class Undefined(override val value: String) : StrategyType(value)
}
