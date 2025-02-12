package com.trading.scalpel.router

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.trading.core.domain.router.AppRoute
import com.trading.core.domain.router.RouterAnimation
import com.trading.core.router.AppNavigationRegistry
import com.trading.core.router.composableWithAnimation
import com.trading.scalpel.view.SplashPage
import javax.inject.Inject

class SplashRouterRegistry @Inject constructor(
    private val router: SplashRouter,
) : AppNavigationRegistry {

    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        router.setController(navController)

        composableWithAnimation(
            route = AppRoute.Splash.route, animation = RouterAnimation.FADE
        ) { SplashPage() }
    }

    override fun getNavigationController(): NavHostController {
        TODO("Not yet implemented")
    }
}
