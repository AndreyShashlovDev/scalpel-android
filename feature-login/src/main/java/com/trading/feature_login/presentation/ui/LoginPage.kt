package com.trading.feature_login.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.trading.core.components.AddressView
import com.trading.core.components.AppButtonView
import com.trading.core.components.AppLoadingView
import com.trading.core.components.AppSingleLineView
import com.trading.core.components.AppTitleView
import com.trading.core.components.ComponentSize
import com.trading.core.components.PageLayoutView
import com.trading.core.components.preview.CompletePreview
import com.trading.core.components.theme.ScalpelTheme
import com.trading.core.utility.evm.Address
import com.trading.feature_login.domain.LoginPresenter
import com.trading.feature_login.presentation.model.LoginPageStatus
import com.trading.feature_login.presentation.model.LoginState

@Composable
fun LoginPage() {
    val presenter: LoginPresenter = hiltViewModel()
    val state by presenter.state.collectAsState()

    PageLayoutView {
        LoginContent(state = state,
                     onConnectClick = {
                         if (state.walletAddress == null) {
                             presenter.onConnectWalletClick()
                         } else {
                             presenter.onDisconnectWalletClick()
                         }
                     },

                     onLoginClick = { presenter.onLoginClick() },
                     onDemoClick = { presenter.onDemoClick() })
    }
}

@Composable
private fun LoginContent(
    modifier: Modifier = Modifier,
    state: LoginState = LoginState(),
    onConnectClick: () -> Unit,
    onLoginClick: () -> Unit,
    onDemoClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        AppTitleView(
            text = "Scalpel",
            size = ComponentSize.LARGE
        )

        MainContentView(state = state,
                        onConnectClick = { onConnectClick() },
                        onLoginClick = { onLoginClick() },
                        onDemoClick = { onDemoClick() })
    }
}

@Composable()
private fun MainContentView(
    state: LoginState = LoginState(),
    onConnectClick: () -> Unit,
    onLoginClick: () -> Unit,
    onDemoClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .heightIn(
                min = 460.dp,
                max = 460.dp
            )
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AppSingleLineView(
            text = "Authorization",
            color = MaterialTheme.colorScheme.primary,
            size = ComponentSize.LARGE
        )

        Spacer(modifier = Modifier.height(48.dp))

        if (state.status is LoginPageStatus.WalletInitialization || state.status is LoginPageStatus.Authorization) {
            AppSingleLineView(
                text = when (state.status) {
                    is LoginPageStatus.WalletInitialization -> "Wallet initialization..."
                    else -> "Authorization"
                }
            )
            AppLoadingView()

        } else {
            LoginButtonsSection(
                walletAddress = state.walletAddress,
                onConnectClick = onConnectClick,
                onLoginClick = onLoginClick,
                onDemoClick = onDemoClick
            )
        }
    }
}

@Composable
private fun LoginButtonsSection(
    walletAddress: Address?,
    onConnectClick: () -> Unit,
    onLoginClick: () -> Unit,
    onDemoClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        walletAddress?.let {
            AddressView(
                address = it,
                size = ComponentSize.MEDIUM
            )
        }

        AppButtonView(
            text = if (walletAddress != null) "Disconnect" else "Connect wallet",
            onClick = onConnectClick
        )

        AppButtonView(
            text = "Login",
            onClick = onLoginClick,
            enabled = walletAddress != null,
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButtonView(
            text = "Demo",
            onClick = onDemoClick
        )
    }
}

@Composable()
@CompletePreview()
private fun PagePreview(
    state: LoginState = LoginState(
        status = LoginPageStatus.WalletNotConnected
    ),
) {
    ScalpelTheme {
        PageLayoutView {
            LoginContent(state = state,
                         onConnectClick = {},
                         onLoginClick = {},
                         onDemoClick = {})
        }
    }
}
