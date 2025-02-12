package com.trading.scalpel.router

import com.trading.core.router.BaseRouter

interface SplashRouter : BaseRouter {

    fun navigateToOrders()

    fun navigateToLogin()
}
