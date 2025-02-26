package com.trading.feature_strategies.domain.model

import com.trading.core.domain.evm.Address
import com.trading.core.domain.network.model.api.ChainType
import java.math.BigDecimal
import java.math.BigInteger

data class Currency(
    val symbol: String,
    val name: String,
    val address: Address,
    val chain: ChainType,
    val decimal: String,
    val isStable: Boolean,
    val price: CurrencyPrice? = null
) {
    fun valueTo(value: String): BigDecimal {
        return value.toBigDecimal()
            .divide(decimal.toBigDecimal())
    }

    fun valueTo(value: BigInteger): BigDecimal {
        return value.toBigDecimal()
            .divide(decimal.toBigDecimal())
    }
}
