package com.trading.feature_strategies.router

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.trading.core.domain.router.AppRoute
import com.trading.core.domain.router.RouterAnimation
import com.trading.core.router.AppNavigationRegistry
import com.trading.core.router.composableWithAnimation
import com.trading.feature_strategies.view.StrategiesPage
import javax.inject.Inject

class StrategiesRouterRegistry @Inject constructor(
    val router: StrategiesRouter,
) : AppNavigationRegistry {

    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        router.setController(navController)

        composableWithAnimation(
            route = AppRoute.Strategies.route, animation = RouterAnimation.FADE
        ) { StrategiesPage() }
    }

    override fun getNavigationController(): NavHostController {
        TODO("Not yet implemented")
    }
}
