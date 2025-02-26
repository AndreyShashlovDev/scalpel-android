package com.trading.feature_strategies.presentation.model

import com.trading.core.domain.evm.Address

data class CurrencyUiModel(
    val symbol: String,
    val address: Address,
)
