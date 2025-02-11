package com.trading.feature_login.presentation.model

import com.trading.core.utility.evm.Address

sealed class LoginPageStatus {
    data object WalletInitialization : LoginPageStatus()
    data object WalletNotConnected : LoginPageStatus()
    data object WalletConnected : LoginPageStatus()
    data object Authorization : LoginPageStatus()
}

data class LoginState(
    val status: LoginPageStatus = LoginPageStatus.WalletInitialization,
    val walletAddress: Address? = null,
)
