package com.trading.feature_strategies.domain.model

import com.trading.core.domain.evm.Address
import com.trading.core.domain.network.model.api.ChainType
import java.math.RoundingMode

data class Currency(
    val symbol: String,
    val name: String,
    val address: Address,
    val chain: ChainType,
    val decimal: String,
    val isStable: Boolean,
//    val price: CurrencyPriceResponse? = null
) {
    fun valueTo(value: String, decimals: Int = 4): Double {
        return value.toBigDecimal()
            .divide(decimal.toBigDecimal())
            .setScale(decimals, RoundingMode.DOWN)
            .toDouble()
    }
}
