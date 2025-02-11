package com.trading.core.router

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface AppNavigationRegistry {

    fun NavGraphBuilder.registerGraph(navController: NavHostController)

    fun getNavigationController(): NavHostController
}
