@file:OptIn(ExperimentalMaterialNavigationApi::class)

package com.trading.scalpel.presentation.ui

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.trading.core.router.AppNavigationRegistry
import com.trading.core.router.AppRoute

@Composable
fun AppContent(
    navigationRegistries: Set<AppNavigationRegistry>,
    modifier: Modifier = Modifier,
) {

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val bottomSheetNavigator = BottomSheetNavigator(sheetState)
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        modifier = Modifier.navigationBarsPadding(),
    ) {
        NavHost(
            navController = navController,
            startDestination = AppRoute.Login.route,
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