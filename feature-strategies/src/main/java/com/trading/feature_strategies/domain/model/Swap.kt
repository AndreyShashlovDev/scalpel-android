package com.trading.feature_strategies.domain.model

import com.trading.core.domain.network.model.api.ChainType
import com.trading.core.domain.network.model.api.SwapState
import com.trading.core.domain.evm.Address
import com.trading.core.domain.evm.TransactionHash
import java.math.BigInteger
import java.util.Date

data class Swap(
    val id: Int,
    val chain: ChainType,
    val currencyFrom: Address,
    val currencyTo: Address,
    val valueFrom: BigInteger,
    val valueTo: BigInteger? = null,
    val exchangeUsdPrice: BigInteger,
    val profit: String? = null,
    val scalpelFeeAmount: String? = null,
    val accumulatorFeeAmount: String? = null,
    val txHash: TransactionHash? = null,
    val txFee: String? = null,
    val state: SwapState,
    val updateAt: Date
)
