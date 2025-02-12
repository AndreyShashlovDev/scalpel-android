package com.trading.scalpel.router

import com.trading.core.domain.router.AppRoute
import com.trading.core.router.AbstractBaseRouter
import javax.inject.Inject

class SplashRouterImpl @Inject constructor() : AbstractBaseRouter(), SplashRouter {

    override fun navigateToOrders() {
        navController?.navigate(AppRoute.Orders.route) {
            popUpTo(0) { inclusive = true }
        }
    }

    override fun navigateToLogin() {
        navController?.navigate(AppRoute.Login.route) {
            popUpTo(0) { inclusive = true }
        }
    }
}
