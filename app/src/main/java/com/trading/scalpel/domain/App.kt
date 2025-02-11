package com.trading.scalpel.domain

import android.app.Application
import com.trading.core.utility.security.isUsingProxy
import com.trading.core.utility.security.isUsingVPN
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val isProxy = isUsingProxy()
        val isVpn = isUsingVPN(this.applicationContext)

        if (isVpn || isProxy) {
            throw Exception("VPN OR Proxy")
        }
    }
}
