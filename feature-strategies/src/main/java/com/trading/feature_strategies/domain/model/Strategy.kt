package com.trading.feature_strategies.domain.model

import com.trading.core.domain.network.model.api.ChainType
import com.trading.core.domain.network.model.api.StrategyStatusType
import com.trading.core.domain.network.model.api.StrategyType
import java.util.Date

data class Strategy(
    val chain: ChainType,
    val type: StrategyType,
    val orderHash: String,
    val wallet: String,
    val currencyA: Currency,
    val currencyB: Currency,
    val totalAmountA: String,
    val totalAmountB: String,
    val adaptiveUsdPrice: String? = null,
    val options: StrategyOptions,
    val initialAmountA: String,
    val approvedA: Boolean,
    val approvedB: Boolean,
    val status: StrategyStatusType,
    val gasLimit: String,
    val createdAt: Date
)
