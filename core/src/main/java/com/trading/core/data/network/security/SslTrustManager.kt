package com.trading.core.data.network.security

import android.content.Context
import com.trading.core.R
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class SslTrustManager(
    private val context: Context
) {
    fun applyTrustManager(okHttpBuilder: OkHttpClient.Builder): OkHttpClient.Builder {
        return try {
            val (sslContext, trustManager) = createSslContext()
            val certificatePinner = createCertificatePinner()

            okHttpBuilder.sslSocketFactory(
                sslContext.socketFactory, trustManager
            )
                .certificatePinner(certificatePinner)
        } catch (e: Exception) {
            throw SecurityException("Failed to initialize SSL configuration", e)
        }
    }

    private fun createSslContext(): Pair<SSLContext, X509TrustManager> {
        val certInput = context.resources.openRawResource(R.raw.base_api_cert)
            .use { input ->
                CertificateFactory.getInstance("X.509")
                    .generateCertificate(input)
            }

        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            .apply {
                load(null, null)
                setCertificateEntry("ca", certInput)
            }

        val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            .apply {
                init(keyStore)
            }

        val trustManager = tmf.trustManagers[0] as X509TrustManager

        return SSLContext.getInstance("TLS")
            .apply {
                init(null, tmf.trustManagers, null)
            } to trustManager
    }

    private fun createCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder()
            .add(
                context.getString(R.string.base_api_host),
                "sha256/${context.getString(R.string.base_api_cert_open_key_sha256)}"
            )
            .build()
    }
}