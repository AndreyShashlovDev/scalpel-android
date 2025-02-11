package com.trading.core.router

import androidx.navigation.NavController

interface RouterProvider {

    fun setController(controller: NavController)
    fun navigateBack()
    fun popUpTo(route: String, inclusive: () -> Boolean)
}
