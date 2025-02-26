package com.trading.core.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.trading.core.view.theme.ScalpelTheme

data class AppBarConfig(
    val title: String
)


@Composable
fun PageLayoutView(
    appBar: AppBarConfig? = null,
    content: @Composable () -> Unit,
) {

    ScalpelTheme {
        Scaffold(topBar = {
            if (appBar != null) {
                AppBarView(appBar)
            }
        }) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    config: AppBarConfig
) {
    TopAppBar(title = { AppTitleView(text = config.title, size = ComponentSize.SMALL) },
              navigationIcon = {

              },
              actions = {

              })
}
