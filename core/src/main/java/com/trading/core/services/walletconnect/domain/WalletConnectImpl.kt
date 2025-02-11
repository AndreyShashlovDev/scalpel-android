package com.trading.core.services.walletconnect.domain

import android.app.Application
import com.reown.android.Core
import com.reown.android.CoreClient
import com.reown.android.relay.ConnectionType
import com.reown.appkit.client.AppKit
import com.reown.appkit.client.Modal
import com.reown.appkit.presets.AppKitChainsPresets
import com.reown.appkit.ui.openAppKit
import com.trading.core.BuildConfig
import com.trading.core.services.walletconnect.presentation.WalletConnectRegistry
import com.trading.core.utility.IOScope
import com.trading.core.utility.evm.Address
import com.trading.core.utility.tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class WalletConnectImpl(
    private val app: Application,
    private val walletConnectRegistry: WalletConnectRegistry,
) : AppKit.ModalDelegate, CoreClient.CoreDelegate, WalletConnect<Wallet>(), WalletEventListener {

    private val _walletResponse = MutableSharedFlow<WalletResponse>()
    private val _walletState =
        MutableStateFlow<WalletState<Wallet>>(WalletState.Initialization(null))

    private val _isInitialized = MutableStateFlow(false)

    init {
        IOScope.launch {
            val appMetaData = Core.Model.AppMetaData(
                name = "Scalpel",
                description = "Scalpel trading",
                url = BuildConfig.SCALPEL_HOST,
                icons = listOf(),
                redirect = "kotlin-dapp-wc://request",
                appLink = BuildConfig.SCALPEL_HOST,
                linkMode = false
            )

            CoreClient.initialize(
                application = app,
                connectionType = ConnectionType.AUTOMATIC,
                projectId = BuildConfig.REOWN_PROJECT_ID,
                metaData = appMetaData,
                telemetryEnabled = false
            ) {
                Timber.e(
                    "CoreClient.initialize", it.throwable.stackTraceToString()
                )
            }

            AppKit.initialize(Modal.Params.Init(core = CoreClient), onSuccess = {
                Timber.d(
                    "AppKit.initialize", "SUccess"
                )
            }, onError = { error ->
                Timber.e(
                    "AppKit.initialize", error.throwable.stackTraceToString()
                )
            })

            AppKit.setChains(AppKitChainsPresets.ethChains.values.toList())

            CoreClient.setDelegate(this@WalletConnectImpl)
            AppKit.setDelegate(this@WalletConnectImpl)

            _isInitialized.emit(true)

            checkAccountWallet()
        }
    }

    override fun isInitialized(): Flow<Boolean> = _isInitialized.asStateFlow()

    private fun checkAccountWallet() {
        IOScope.launch {
            val acc = AppKit.getAccount()
            val address = Address.from(acc?.address)
            val wallet = if (address == null) null else SimpleEvmWallet(address)
            _walletState.emit(
                if (!_isInitialized.value) {
                    WalletState.Initialization(wallet)
                } else {
                    WalletState.WalletChanged(wallet)
                }
            )
        }

    }

    override fun isConnected(): Boolean {
        return _isInitialized.value && _walletState.value.wallet != null
    }

    override fun connect() {
        if (_isInitialized.value) {
            walletConnectRegistry.getNavigationController()
                .openAppKit(shouldOpenChooseNetwork = false, onError = {
                    Timber.e(
                        "openAppKit", it
                    )
                })
        }
    }

    override fun disconnect() {
        AppKit.disconnect(
            onSuccess = {
                checkAccountWallet()

                Timber.d(tag(this), "Disconnect success")
            },
            onError = {
                Timber.d(tag(this), "Disconnect fail")
            },
        )
    }

    override fun getConnection(): Wallet? {
        return _walletState.value.wallet
    }

    override fun observe(): Flow<WalletState<Wallet>> = _walletState.asStateFlow()


    override fun onConnectionStateChange(state: Modal.Model.ConnectionState) {}

    override fun onError(error: Modal.Model.Error) {}

    override fun onProposalExpired(proposal: Modal.Model.ExpiredProposal) {}

    override fun onRequestExpired(request: Modal.Model.ExpiredRequest) {}

    override fun onSessionApproved(approvedSession: Modal.Model.ApprovedSession) {
        checkAccountWallet()
    }

    override fun onSessionDelete(deletedSession: Modal.Model.DeletedSession) {
        checkAccountWallet()
    }

    override fun onSessionEvent(sessionEvent: Modal.Model.Event) {
        Timber.d(
            "dsds", sessionEvent
        )
    }

    @Deprecated(
        "Use onSessionEvent(Modal.Model.Event) instead. Using both will result in duplicate events.",
        replaceWith = ReplaceWith("onEvent(event)")
    )
    override fun onSessionEvent(sessionEvent: Modal.Model.SessionEvent) {
        Timber.d(
            "dsds", sessionEvent
        )
    }

    override fun onSessionExtend(session: Modal.Model.Session) {
        checkAccountWallet()
    }

    override fun onSessionRejected(rejectedSession: Modal.Model.RejectedSession) {
        checkAccountWallet()
    }

    override fun onSessionRequestResponse(response: Modal.Model.SessionRequestResponse) {
        val result = response.result

        val wallet = _walletState.value.wallet ?: return

        IOScope.launch {
            _walletResponse.emit(
                when (result) {
                    is Modal.Model.JsonRpcResponse.JsonRpcError -> WalletResponse.Error(
                        wallet, response.method, result.code, result.message
                    )

                    is Modal.Model.JsonRpcResponse.JsonRpcResult -> WalletResponse.Success(
                        wallet, response.method, result.result
                    )
                }
            )
        }
    }

    override fun observeEvents(): Flow<WalletResponse> = _walletResponse.asSharedFlow()

    override fun onSessionUpdate(updatedSession: Modal.Model.UpdatedSession) {
        checkAccountWallet()
    }
}
