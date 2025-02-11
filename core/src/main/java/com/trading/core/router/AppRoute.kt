package com.trading.core.router

sealed class AppRoute(val route: String) {
    data object Login : AppRoute("login")
    data object Feed : AppRoute("feed")
    data object Demo : AppRoute("demo")

//    data class Profile(val userId: String) : AppRoute("profile/{userId}") {
//        companion object {
//            const val route = "profile/{userId}"
//        }
//    }
//
//    data object Settings : AppRoute("settings")
//
//    companion object {
//        fun fromRoute(route: String): AppRoute? {
//            return when {
//                route == Login.route -> Login
//                route == Feed.route -> Feed
//                route.startsWith("profile/") -> {
//                    val userId = route.substringAfter("profile/")
//                    Profile(userId)
//                }
//                route == Settings.route -> Settings
//                else -> null
//            }
//        }
//    }
}