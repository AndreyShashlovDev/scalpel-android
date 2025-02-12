package com.trading.core.view.walletconnect

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.reown.appkit.ui.appKitGraph
import com.trading.core.router.AppNavigationRegistry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletConnectRegistry @Inject constructor() : AppNavigationRegistry {

    private var _navController: NavHostController? = null

    override fun NavGraphBuilder.registerGraph(navController: NavHostController) {
        _navController = navController
        appKitGraph(navController)
    }

    override fun getNavigationController(): NavHostController {
        return checkNotNull(_navController) {
            "NavController not initialized. Make sure registerGraph was called first"
        }
    }
}
