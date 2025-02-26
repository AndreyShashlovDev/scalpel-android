package com.trading.feature_strategies.domain.model

import com.trading.core.domain.evm.Address
import com.trading.core.domain.network.model.api.ChainType
import com.trading.core.domain.network.model.api.StrategyStatusType
import com.trading.core.domain.network.model.api.StrategyType
import java.math.BigInteger
import java.util.Date

data class Strategy(
    val chain: ChainType,
    val type: StrategyType,
    val orderHash: String,
    val wallet: Address,
    val currencyA: Currency,
    val currencyB: Currency,
    val totalAmountA: BigInteger,
    val totalAmountB: BigInteger,
    val adaptiveUsdPrice: BigInteger? = null,
    val options: StrategyOptions,
    val initialAmountA: BigInteger,
    val approvedA: Boolean,
    val approvedB: Boolean,
    val status: StrategyStatusType,
    val gasLimit: BigInteger,
    val createdAt: Date
)
