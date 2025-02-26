package com.trading.feature_login.data.mapper

import com.trading.feature_login.data.model.AuthRequestDto
import com.trading.feature_login.domain.repository.auth.model.AuthRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthMapper @Inject constructor() {

    fun mapToCreateDto(request: AuthRequest): AuthRequestDto = AuthRequestDto(
        address = request.address.value,
        message = request.message,
        sign = request.sign
    )
}
