package com.trading.core.domain.router


interface RouterLogger {

    fun logNavigationEvent(from: AppRoute, to: AppRoute, params: Map<String, Any>? = null)
}
