package com.trading.scalpel

import android.app.Application
import com.trading.core.utility.security.isRooted
import com.trading.core.utility.security.isUsingProxy
import com.trading.core.utility.security.isUsingVPN
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val isProxy = isUsingProxy()
        val isVpn = isUsingVPN(this.applicationContext)
        val isRooted = isRooted()

        if (isVpn || isProxy || isRooted) {
            throw Exception("Detect VPN/Proxy/Rooted")
        }
    }
}
