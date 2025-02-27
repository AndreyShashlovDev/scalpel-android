package com.trading.core.router

import androidx.navigation.NavController

abstract class AbstractBaseRouter : BaseRouter {

    protected var navController: NavController? = null

    override fun currentRouteName(): String? = navController?.currentDestination?.route

    override fun setController(controller: NavController) {
        navController = controller
    }

    override fun navigateBack() {
        navController?.let { navController ->
            if (navController.previousBackStackEntry != null) {
                navController.popBackStack()
            }
        }
    }

    override fun popUpTo(route: String, inclusive: () -> Boolean) {
        navController?.navigate(route) {
            popUpTo(route) {
                this.inclusive = inclusive()
            }
        }
    }
}
