package com.trading.feature_orders.router

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.trading.core.domain.router.AppRoute
import com.trading.core.domain.router.RouterAnimation
import com.trading.core.router.AppNavigationRegistry
import com.trading.core.router.composableWithAnimation
import com.trading.feature_orders.view.OrdersPage
import javax.inject.Inject

class OrdersRouterRegistry @Inject constructor(
    val router: OrdersRouter,
) : AppNavigationRegistry {

    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        router.setController(navController)

        composableWithAnimation(
            route = AppRoute.Orders.route, animation = RouterAnimation.FADE
        ) { OrdersPage() }
    }

    override fun getNavigationController(): NavHostController {
        TODO("Not yet implemented")
    }
}
