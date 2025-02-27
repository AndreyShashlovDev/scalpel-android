package com.trading.core.domain.network.model.api

import androidx.annotation.StringRes
import com.trading.core.R
import com.trading.core.domain.network.serializer.StrategyTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StrategyTypeSerializer::class)
sealed class StrategyType(open val value: String, @StringRes val resId: Int) {
    data object ClassicScalpel :
        StrategyType("CLASSIC_SCALPEL", R.string.strategy_type_scalpel_classic)

    data object ClassicScalpelTest :
        StrategyType("CLASSIC_SCALPEL_TEST", R.string.strategy_type_undefined)

    data object ClassicScalpelTestV2 :
        StrategyType("CLASSIC_SCALPEL_TEST_V2", R.string.strategy_type_undefined)

    data class Undefined(override val value: String) :
        StrategyType(value, R.string.strategy_type_undefined)
}
