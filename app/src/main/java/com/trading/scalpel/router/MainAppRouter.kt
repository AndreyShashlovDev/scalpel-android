package com.trading.scalpel.router

import com.trading.core.router.BaseRouter

interface MainAppRouter : BaseRouter {

    fun navigateToOrders()

    fun navigateToLogin()
}
