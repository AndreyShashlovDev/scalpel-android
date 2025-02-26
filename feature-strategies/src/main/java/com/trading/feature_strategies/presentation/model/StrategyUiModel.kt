package com.trading.feature_strategies.presentation.model

import com.trading.core.domain.evm.Address
import com.trading.core.domain.network.model.api.ChainType
import com.trading.core.domain.network.model.api.StrategyStatusType
import com.trading.core.domain.network.model.api.StrategyType
import java.math.BigDecimal
import java.util.Date

data class StrategyUiModel(
    val chain: ChainType,
    val status: StrategyStatusType,
    val type: StrategyType,
    val wallet: Address,
    val currencyBUsdPrice: BigDecimal?,
    val currencyA: CurrencyUiModel,
    val currencyB: CurrencyUiModel,
    val totalAmountA: BigDecimal,
    val totalAmountB: BigDecimal,
    val totalUsdAmountB: BigDecimal,
    val initialAmountA: BigDecimal,
    val totalUsdProfit: BigDecimal,
    val createdAt: Date
)
