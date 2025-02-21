@file:OptIn(ExperimentalMaterialNavigationApi::class)

package com.trading.scalpel.view

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.trading.core.domain.router.AppRoute
import com.trading.core.router.AppNavigationRegistry
import com.trading.scalpel.presentation.MainAppPresenter

@Composable
fun AppContent(
    navigationRegistries: Set<AppNavigationRegistry>,
    startupPresenter: MainAppPresenter,
    modifier: Modifier = Modifier,
) {

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )

    val bottomSheetNavigator = BottomSheetNavigator(sheetState)
    val navController = rememberNavController(bottomSheetNavigator)

    LaunchedEffect(Unit) {
        startupPresenter.determineStartDestination()
    }

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        modifier = Modifier.navigationBarsPadding(),
    ) {
        NavHost(
            navController = navController,
            startDestination = AppRoute.Splash.route,
            modifier = modifier
        ) {
            navigationRegistries.forEach { registry ->
                with(registry) {
                    registerGraph(navController)
                }
            }
        }
    }
}
