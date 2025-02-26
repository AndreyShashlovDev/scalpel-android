package com.trading.feature_strategies.data.model

import com.trading.core.domain.evm.Address
import com.trading.core.domain.network.model.api.ChainType
import com.trading.core.domain.network.model.api.StrategyStatusType
import com.trading.core.domain.network.model.api.StrategyType
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class StrategyResponse(
    val chain: ChainType,
    val type: StrategyType,
    val orderHash: String,
    val wallet: Address,
    val currencyA: CurrencyResponse,
    val currencyB: CurrencyResponse,
    val totalAmountA: String,
    val totalAmountB: String,
    val adaptiveUsdPrice: String? = null,
    val options: StrategyOptionsResponse,
    val initialAmountA: String,
    val approvedA: Boolean,
    val approvedB: Boolean,
    val status: StrategyStatusType,
    val gasLimit: String,
    @Contextual val createdAt: Date
)
