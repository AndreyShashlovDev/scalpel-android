package com.trading.core.router


interface RouterLogger {

    fun logNavigationEvent(from: AppRoute, to: AppRoute, params: Map<String, Any>? = null)
}
