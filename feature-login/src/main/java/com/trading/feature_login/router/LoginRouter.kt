package com.trading.feature_login.router

import com.trading.core.router.BaseRouter

interface LoginRouter : BaseRouter {

    fun navigateToOrders()

    fun navigateToDemo()
}
