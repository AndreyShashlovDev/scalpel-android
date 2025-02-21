package com.trading.core.utility

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object AppScope {
    val io = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    val default = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    val main = CoroutineScope(SupervisorJob() + Dispatchers.Main)
}