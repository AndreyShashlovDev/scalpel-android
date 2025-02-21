package com.trading.core.di.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.trading.core.BuildConfig
import com.trading.core.data.network.interceptors.ApiErrorInterceptor
import com.trading.core.data.network.interceptors.AuthInterceptor
import com.trading.core.data.network.security.SslTrustManager
import com.trading.core.domain.network.ApiErrorNotifier
import com.trading.core.domain.network.serializer.DateSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlQualifier

@Module
@InstallIn(SingletonComponent::class)
object DataNetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
        serializersModule = SerializersModule {
            contextual(Date::class, DateSerializer)
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        application: Application,
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        apiErrorInterceptor: ApiErrorInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiErrorInterceptor)
            .connectTimeout(
                30, TimeUnit.SECONDS
            )
            .readTimeout(
                30, TimeUnit.SECONDS
            )
            .writeTimeout(
                30, TimeUnit.SECONDS
            )
        return SslTrustManager(application.applicationContext).applyTrustManager(builder)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiErrorInterceptor(
        apiErrorNotifier: ApiErrorNotifier,
        app: Application,
    ): ApiErrorInterceptor {
        return ApiErrorInterceptor(
            apiErrorNotifier,
            app.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
        @BaseUrlQualifier baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    @BaseUrlQualifier
    fun provideBaseUrl(): String = BuildConfig.SCALPEL_API_URL
}
