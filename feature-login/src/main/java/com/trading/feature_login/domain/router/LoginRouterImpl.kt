package com.trading.feature_login.domain.router

import com.trading.core.router.AbstractBaseRouter
import com.trading.core.router.AppRoute
import javax.inject.Inject

class LoginRouterImpl @Inject constructor() : AbstractBaseRouter(), LoginRouter {

    override fun navigateToFeed() {
//        logger.logNavigationEvent(
//            AppRoute.Login,
//            AppRoute.Feed
//        )
        navController?.navigate(com.trading.core.router.AppRoute.Feed.route) {
            popUpTo(AppRoute.Login.route) { inclusive = true }
        }
    }

    override fun navigateToDemo() {
//        logger.logNavigationEvent(
//            AppRoute.Login,
//            AppRoute.Demo
//        )
        navController?.navigate(com.trading.core.router.AppRoute.Feed.route) {
            popUpTo(AppRoute.Login.route) { inclusive = true }
        }
    }
}
