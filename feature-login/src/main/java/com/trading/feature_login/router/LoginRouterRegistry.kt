package com.trading.feature_login.router

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.trading.core.router.AppNavigationRegistry
import com.trading.core.domain.router.AppRoute
import com.trading.core.domain.router.RouterAnimation
import com.trading.core.router.composableWithAnimation
import com.trading.feature_login.view.LoginPage
import javax.inject.Inject

class LoginRouterRegistry @Inject constructor(
    val router: LoginRouter,
) : AppNavigationRegistry {

    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        router.setController(navController)

        composableWithAnimation(
            route = AppRoute.Login.route,
            animation = RouterAnimation.FADE
        ) { LoginPage() }
    }

    override fun getNavigationController(): NavHostController {
        TODO("Not yet implemented")
    }
}
