package com.trading.feature_strategies.data.model

import com.trading.core.domain.evm.Address
import com.trading.core.domain.evm.TransactionHash
import com.trading.core.domain.network.model.api.ChainType
import com.trading.core.domain.network.model.api.SwapState
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class SwapResponse(
    val id: Int,
    val chain: ChainType,
    val currencyFrom: Address,
    val currencyTo: Address,
    val valueFrom: String,
    val valueTo: String? = null,
    val exchangeUsdPrice: String,
    val profit: String? = null,
    val scalpelFeeAmount: String? = null,
    val accumulatorFeeAmount: String? = null,
    val txHash: TransactionHash? = null,
    val txFee: String? = null,
    val state: SwapState,
    @Contextual val updateAt: Date
)
