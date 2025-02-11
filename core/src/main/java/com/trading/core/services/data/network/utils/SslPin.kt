package com.trading.core.services.data.network.utils

import android.content.Context
import com.trading.core.R
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

fun ApplyTrustManager(context: Context, okHttpBuilder: OkHttpClient.Builder): OkHttpClient.Builder {
    val cf = CertificateFactory.getInstance("X.509")
    val certInput: InputStream = context.resources.openRawResource(R.raw.base_api_cert)
    val ca = cf.generateCertificate(certInput)
    certInput.close()

    val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        .apply {
            load(null, null)
            setCertificateEntry("ca", ca)
        }

    val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        .apply { init(keyStore) }

    val sslContext = SSLContext.getInstance("TLS")
        .apply { init(null, tmf.trustManagers, null) }

    val certificatePinner = CertificatePinner.Builder()
        .add(
            context.getString(R.string.base_api_host),
            "sha256/${context.getString(R.string.base_api_cert_open_key_sha256)}"
        )
        .build()


    return okHttpBuilder.sslSocketFactory(
        sslContext.socketFactory, tmf.trustManagers[0] as javax.net.ssl.X509TrustManager
    )
        .certificatePinner(certificatePinner)
}