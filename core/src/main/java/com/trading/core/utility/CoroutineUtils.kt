package com.trading.core.utility

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

val IOScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
