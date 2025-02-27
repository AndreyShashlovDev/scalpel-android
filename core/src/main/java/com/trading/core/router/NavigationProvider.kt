package com.trading.core.router

import androidx.navigation.NavController

interface RouterProvider {
    fun currentRouteName(): String?
    fun setController(controller: NavController)
    fun navigateBack()
    fun popUpTo(route: String, inclusive: () -> Boolean)
}
