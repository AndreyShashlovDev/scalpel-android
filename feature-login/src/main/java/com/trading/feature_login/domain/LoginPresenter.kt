package com.trading.feature_login.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trading.core.services.walletconnect.domain.Wallet
import com.trading.core.services.walletconnect.domain.WalletConnect
import com.trading.core.services.walletconnect.domain.WalletState
import com.trading.feature_login.domain.interactor.MakeAuthInteractor
import com.trading.feature_login.domain.router.LoginRouter
import com.trading.feature_login.presentation.model.LoginPageStatus
import com.trading.feature_login.presentation.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginPresenter @Inject constructor(
    private val router: LoginRouter,
    private val walletConnect: WalletConnect<Wallet>,
    private val makeAuthInteractor: MakeAuthInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
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
