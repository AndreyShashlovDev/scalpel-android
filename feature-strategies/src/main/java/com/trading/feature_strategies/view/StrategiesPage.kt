package com.trading.feature_strategies.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.trading.core.view.components.AppBarConfig
import com.trading.core.view.components.ListState
import com.trading.core.view.components.PageLayoutView
import com.trading.core.view.preview.CompletePreview
import com.trading.core.view.theme.ScalpelTheme
import com.trading.feature_orders.R
import com.trading.feature_strategies.presentation.StrategiesPagePresenter
import com.trading.feature_strategies.presentation.model.StrategiesPageState
import com.trading.feature_strategies.view.list.StrategiesListView

@Composable
fun StrategiesPage() {
    val presenter: StrategiesPagePresenter = hiltViewModel()
    val state by presenter.state.collectAsState()

    PageContent(state = state, onFetchNext = { presenter.onFetchNext() })
}

@Composable
@CompletePreview
private fun PageContent(
    state: StrategiesPageState = StrategiesPageState(
        listState = ListState.Loading,
    ),
    onFetchNext: () -> Unit = {},
) {
    ScalpelTheme {
        PageLayoutView(
            appBar = AppBarConfig(title = stringResource(R.string.strategies_page_title))
        ) {
            StrategiesListView(
                listState = state.listState,
                items = state.items,
                onFetchNext = { onFetchNext() })
        }
    }
}
