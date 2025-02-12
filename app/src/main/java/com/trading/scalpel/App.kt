package com.trading.scalpel

import android.app.Application
import android.os.StrictMode
import com.trading.core.BuildConfig
import com.trading.core.utility.security.isRooted
import com.trading.core.utility.security.isUsingProxy
import com.trading.core.utility.security.isUsingVPN
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }

        val isProxy = isUsingProxy()
        val isVpn = isUsingVPN(this.applicationContext)
        val isRooted = isRooted()

        if (isVpn || isProxy || isRooted) {
            throw Exception("Detect VPN/Proxy/Rooted")
        }
    }


    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .penaltyDialog()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .detectLeakedRegistrationObjects()
                .detectFileUriExposure()
                .penaltyLog()
                .build()
        )
    }
}
