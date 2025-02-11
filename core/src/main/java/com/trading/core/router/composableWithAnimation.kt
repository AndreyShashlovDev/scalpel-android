package com.trading.core.router

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.composableWithAnimation(
    route: String,
    animation: RouterAnimation = RouterAnimation.SLIDE,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        enterTransition = {
            when (animation) {
                RouterAnimation.SLIDE -> slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )

                RouterAnimation.FADE -> fadeIn(
                    animationSpec = tween(300)
                )

                RouterAnimation.SCALE -> scaleIn(
                    initialScale = 0.8f,
                    animationSpec = tween(300)
                )
            }
        },
        exitTransition = {
            when (animation) {
                RouterAnimation.SLIDE -> slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(300)
                )

                RouterAnimation.FADE -> fadeOut(
                    animationSpec = tween(300)
                )

                RouterAnimation.SCALE -> scaleOut(
                    targetScale = 1.2f,
                    animationSpec = tween(300)
                )
            }
        }) {
        content(it)
    }
}
