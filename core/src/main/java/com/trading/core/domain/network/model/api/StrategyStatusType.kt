package com.trading.core.domain.network.model.api

import com.trading.core.domain.network.serializer.StrategyStatusTypeSerializer
import kotlinx.serialization.Serializable


@Serializable(with = StrategyStatusTypeSerializer::class)
sealed class StrategyStatusType(open val value: String) {
    data object Created : StrategyStatusType("CREATED")
    data object ApproveInProgress : StrategyStatusType("APPROVE_IN_PROGRESS")
    data object InProgress : StrategyStatusType("IN_PROGRESS")
    data object UserActionRequired : StrategyStatusType("USER_ACTION_REQUIRED")
    data object Paused : StrategyStatusType("PAUSED")
    data object Canceled : StrategyStatusType("CANCELED")

    data class Undefined(override val value: String) : StrategyStatusType(value)
}
