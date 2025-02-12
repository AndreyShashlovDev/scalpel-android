package com.trading.core.di.walletconnect

import android.app.Application
import com.trading.core.domain.storage.Storage
import com.trading.core.domain.walletconnect.Wallet
import com.trading.core.domain.walletconnect.WalletConnect
import com.trading.core.data.walletconnect.WalletConnectImpl
import com.trading.core.domain.walletconnect.WalletEventListener
import com.trading.core.domain.walletconnect.signer.MessageSigner
import com.trading.core.domain.walletconnect.signer.MessageSignerImpl
import com.trading.core.view.walletconnect.WalletConnectRegistry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InternalWalletConnectImpl

@Module
@InstallIn(SingletonComponent::class)
object WalletConnectModule {

    @Provides
    @Singleton
    @InternalWalletConnectImpl
    fun provideImpl(
        app: Application,
        walletConnectRegistry: WalletConnectRegistry,
        storage: Storage,
    ): WalletConnectImpl = WalletConnectImpl(
        app, walletConnectRegistry, storage
    )

    @Provides
    @Singleton
    fun provideWalletConnect(
        @InternalWalletConnectImpl impl: WalletConnectImpl,
    ): WalletConnect<Wallet> = impl

    @Provides
    @Singleton
    fun provideWalletEventListener(
        @InternalWalletConnectImpl impl: WalletConnectImpl,
    ): WalletEventListener = impl

    @Provides
    @Singleton
    fun provideMessageSigner(
        walletEventListener: WalletEventListener,
        walletConnect: WalletConnect<Wallet>,
    ): MessageSigner = MessageSignerImpl(
        walletEventListener, walletConnect
    )
}