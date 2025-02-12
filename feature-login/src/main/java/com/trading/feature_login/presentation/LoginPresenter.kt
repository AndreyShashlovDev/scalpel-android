package com.trading.feature_login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trading.core.domain.auth.TokenProvider
import com.trading.core.domain.walletconnect.Wallet
import com.trading.core.domain.walletconnect.WalletConnect
import com.trading.core.domain.walletconnect.WalletState
import com.trading.feature_login.domain.interactor.MakeAuthInteractor
import com.trading.feature_login.presentation.model.LoginPageStatus
import com.trading.feature_login.presentation.model.LoginState
import com.trading.feature_login.router.LoginRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginPresenter @Inject constructor(
    private val router: LoginRouter,
    private val walletConnect: WalletConnect<Wallet>,
    private val makeAuthInteractor: MakeAuthInteractor,
    private val tokenProvider: TokenProvider
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            tokenProvider.initialize()

            tokenProvider.observe()
                .filter { it != null }
                .collect {
                    router.navigateToOrders()
                }
        }

        viewModelScope.launch {
            walletConnect.observe()
                .collect { state ->
                    val isInitialization = state is WalletState.Initialization

                    _state.update {
                        it.copy(
                            status = if (isInitialization) {
                                LoginPageStatus.WalletInitialization
                            } else if (state.wallet != null) {
                                LoginPageStatus.WalletConnected
                            } else {
                                LoginPageStatus.WalletNotConnected
                            },
                            walletAddress = state.wallet?.address,
                        )
                    }
                }
        }
    }

    fun onConnectWalletClick() {
        walletConnect.connect()
    }

    fun onDisconnectWalletClick() {
        walletConnect.disconnect()
    }

    fun onLoginClick() {
        viewModelScope.launch {
            makeAuthInteractor.invoke()
        }
    }

    fun onDemoClick() {
        viewModelScope.launch {
            router.navigateToDemo()
        }
    }
}
