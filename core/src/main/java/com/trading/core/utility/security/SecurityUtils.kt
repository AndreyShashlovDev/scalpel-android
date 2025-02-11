package com.trading.core.utility.security

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import java.io.File

fun isRooted(): Boolean {
    val paths = arrayOf(
        "/system/app/Superuser.apk", "/system/xbin/su", "/system/bin/su", "/sbin/su"
    )
    return paths.any { File(it).exists() }
}

fun isUsingProxy(): Boolean {
    val proxyHost = System.getProperty("http.proxyHost")
    val proxyPort = System.getProperty("http.proxyPort")
    return !proxyHost.isNullOrEmpty() || !proxyPort.isNullOrEmpty()
}

@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun isUsingVPN(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networks = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(networks)

    return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) == true
}