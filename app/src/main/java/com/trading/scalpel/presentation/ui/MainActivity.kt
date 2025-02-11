package com.trading.scalpel.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.trading.core.components.theme.ScalpelTheme
import com.trading.core.router.AppNavigationRegistry
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    @JvmSuppressWildcards
    lateinit var navigationRegistries: Set<AppNavigationRegistry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContent {
                ScalpelTheme(darkTheme = true) {
                    AppContent(
                        navigationRegistries = navigationRegistries
                    )
                }
            }
        } catch (e: Exception) {
            Log.e(
                "MainActivity",
                "Error in onCreate",
                e
            )
        }
    }
}
