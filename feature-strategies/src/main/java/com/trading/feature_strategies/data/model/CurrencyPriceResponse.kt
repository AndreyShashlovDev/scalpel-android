package com.trading.feature_strategies.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class CurrencyPriceResponse(
    val usdtPrice: String,
    val usdtExchangePrice: String,
    val dex: String,
    @Contextual val createdAt: Date
)
