package com.trading.feature_strategies.domain.model

import java.math.BigInteger

data class SimpleHistory(
    val date: Long, val value: BigInteger
)