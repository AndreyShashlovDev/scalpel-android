package com.trading.feature_login.domain.router

import com.trading.core.router.BaseRouter

interface LoginRouter : BaseRouter {

    fun navigateToFeed()

    fun navigateToDemo()
}
