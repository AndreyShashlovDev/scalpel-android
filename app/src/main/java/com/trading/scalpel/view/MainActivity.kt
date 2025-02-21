package com.trading.scalpel.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.trading.core.router.AppNavigationRegistry
import com.trading.core.view.theme.ScalpelTheme
import com.trading.scalpel.presentation.MainAppPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    @JvmSuppressWildcards
    lateinit var navigationRegistries: Set<AppNavigationRegistry>

    @Inject
    lateinit var presenter: MainAppPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContent {
                ScalpelTheme(darkTheme = true) {
                    AppContent(
                        navigationRegistries, presenter
                    )
                }
            }
        } catch (e: Exception) {
            Log.e(
                "MainActivity", "Error in onCreate", e
            )
        }
    }
}
