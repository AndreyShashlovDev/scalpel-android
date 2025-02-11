package com.trading.feature_login.data.model

import kotlinx.serialization.Serializable

@Serializable
class AuthRequestDto(
    val address: String,
    val message: String,
    val sign: String,
)
