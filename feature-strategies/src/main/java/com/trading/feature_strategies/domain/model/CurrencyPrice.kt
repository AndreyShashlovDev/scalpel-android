package com.trading.feature_strategies.domain.model

import java.math.BigInteger
import java.util.Date

data class CurrencyPrice(
    val usdtPrice: BigInteger,
    val usdtExchangePrice: BigInteger,
    val dex: String,
    val createdAt: Date
)
