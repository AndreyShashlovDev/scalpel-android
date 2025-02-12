package com.trading.feature_login.router

import com.trading.core.domain.router.AppRoute
import com.trading.core.router.AbstractBaseRouter
import javax.inject.Inject

class LoginRouterImpl @Inject constructor() : AbstractBaseRouter(), LoginRouter {

    override fun navigateToOrders() {
        navController?.navigate(AppRoute.Orders.route) {
            popUpTo(AppRoute.Login.route) { inclusive = true }
        }
    }

    override fun navigateToDemo() {
        navController?.navigate(AppRoute.Demo.route) {
            popUpTo(AppRoute.Login.route) { inclusive = true }
        }
    }
}
