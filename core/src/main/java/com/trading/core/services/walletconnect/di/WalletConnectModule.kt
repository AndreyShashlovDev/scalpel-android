package com.trading.core.services.walletconnect.di

import android.app.Application
import com.trading.core.services.walletconnect.domain.Wallet
import com.trading.core.services.walletconnect.domain.WalletConnect
import com.trading.core.services.walletconnect.domain.WalletConnectImpl
import com.trading.core.services.walletconnect.domain.WalletEventListener
import com.trading.core.services.walletconnect.domain.signer.MessageSigner
import com.trading.core.services.walletconnect.domain.signer.MessageSignerImpl
import com.trading.core.services.walletconnect.presentation.WalletConnectRegistry
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
    ): WalletConnectImpl = WalletConnectImpl(
        app,
        walletConnectRegistry
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
        walletEventListener,
        walletConnect
    )
}