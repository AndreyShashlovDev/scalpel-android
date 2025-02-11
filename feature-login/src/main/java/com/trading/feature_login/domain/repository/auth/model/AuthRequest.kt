package com.trading.feature_login.domain.repository.auth.model

import com.trading.core.utility.evm.Address

class AuthRequest(
    val address: Address,
    val message: String,
    val sign: String,
)
