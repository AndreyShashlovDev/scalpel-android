package com.trading.core.domain.network.model.api

import androidx.annotation.StringRes
import com.trading.core.R
import com.trading.core.domain.network.serializer.StrategyStatusTypeSerializer
import kotlinx.serialization.Serializable


@Serializable(with = StrategyStatusTypeSerializer::class)
sealed class StrategyStatusType(open val value: String, @StringRes val resId: Int) {
    data object Created : StrategyStatusType("CREATED", R.string.strategy_status_type_created)
    data object ApproveInProgress :
        StrategyStatusType("APPROVE_IN_PROGRESS", R.string.strategy_status_type_approveinprogress)

    data object InProgress :
        StrategyStatusType("IN_PROGRESS", R.string.strategy_status_type_inprogress)

    data object UserActionRequired :
        StrategyStatusType("USER_ACTION_REQUIRED", R.string.strategy_status_type_useractionrequired)

    data object Paused : StrategyStatusType("PAUSED", R.string.strategy_status_type_paused)
    data object Canceled : StrategyStatusType("CANCELED", R.string.strategy_status_type_canceled)

    data class Undefined(override val value: String) :
        StrategyStatusType(value, R.string.strategy_status_type_undefined)
}
