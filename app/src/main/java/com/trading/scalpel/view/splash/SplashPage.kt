package com.trading.scalpel.view.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trading.core.BuildConfig
import com.trading.core.view.components.AppLoadingView
import com.trading.core.view.components.AppTitleView
import com.trading.core.view.components.ComponentSize
import com.trading.core.view.components.PageLayoutView
import com.trading.core.view.preview.CompletePreview

@Composable
@CompletePreview
fun SplashPage() {
    PageLayoutView {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            AppTitleView(text = BuildConfig.APP_NAME, size = ComponentSize.LARGE)

            Column(
                modifier = Modifier
                    .heightIn(
                        min = 460.dp, max = 460.dp
                    )
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AppLoadingView()
            }
        }
    }
}
