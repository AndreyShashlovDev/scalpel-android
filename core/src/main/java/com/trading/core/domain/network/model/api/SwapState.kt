package com.trading.core.domain.network.model.api

import com.trading.core.domain.network.serializer.SwapStateSerializer
import kotlinx.serialization.Serializable

@Serializable(with = SwapStateSerializer::class)
sealed class SwapState(open val value: String) {
    data object WaitForAction : SwapState("WAIT_FOR_ACTION")
    data object WaitExecution : SwapState("WAIT_EXECUTION")
    data object Cancelled : SwapState("CANCELED")
    data object Failed : SwapState("FAILED")
    data object Execution : SwapState("EXECUTION")
    data object ExecutionSuccess : SwapState("EXECUTION_SUCCESS")
    data object ExecutionFailed : SwapState("EXECUTION_FAILED")

    data class Undefined(override val value: String) : SwapState(value)
}
