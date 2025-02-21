package com.trading.core.domain.network.model.api

import kotlinx.serialization.Serializable

@Serializable
data class Pageable<T>(
    val data: List<T>, val total: Int, val page: Int
)
