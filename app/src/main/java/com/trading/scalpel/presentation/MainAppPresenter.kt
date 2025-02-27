package com.trading.scalpel.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trading.core.domain.auth.TokenProvider
import com.trading.core.domain.router.AppRoute
import com.trading.scalpel.router.MainAppRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainAppPresenter @Inject constructor(
    private val tokenProvider: TokenProvider, private val router: MainAppRouter
) : ViewModel() {

    init {
        viewModelScope.launch {
            tokenProvider.observe()
                .collect { token ->
                    if (token == null && tokenProvider.isInitialized()) {
                        router.navigateToLogin()
                    }
                }
        }
    }

    fun determineStartDestination() {
        if (router.currentRouteName() !== AppRoute.Splash.route) {
            return
        }

        viewModelScope.launch {
            tokenProvider.initialize()

            val hasToken = tokenProvider.getToken() != null

            delay(1000)

            if (hasToken) {
                router.navigateToOrders()
            } else {
                router.navigateToLogin()
            }
        }
    }
}