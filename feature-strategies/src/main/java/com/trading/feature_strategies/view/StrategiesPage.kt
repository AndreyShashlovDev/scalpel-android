package com.trading.feature_strategies.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.trading.core.view.components.AppBarConfig
import com.trading.core.view.components.AppSingleLineView
import com.trading.core.view.components.PageLayoutView
import com.trading.core.view.preview.CompletePreview
import com.trading.feature_orders.R
import com.trading.feature_strategies.presentation.StrategiesPagePresenter

@Composable
fun StrategiesPage() {
    val presenter: StrategiesPagePresenter = hiltViewModel()
//    val state by presenter.state.collectAsState()

    PageLayoutView(
        appBar = AppBarConfig(title = stringResource(R.string.strategies_page_title))
    ) {
        AppSingleLineView(text = stringResource(R.string.strategies_page_title))
    }
}


@CompletePreview
@Composable
private fun PagePreview() {
    PageLayoutView(
        appBar = AppBarConfig(title = stringResource(R.string.strategies_page_title))
    ) {
        AppSingleLineView(text = stringResource(R.string.strategies_page_title))
    }
}
