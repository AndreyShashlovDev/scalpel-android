package com.trading.feature_strategies.view.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.trading.core.view.components.BaseListView
import com.trading.core.view.components.ListState
import com.trading.feature_orders.R
import com.trading.feature_strategies.presentation.model.CompositeStrategyUiModel
import com.trading.feature_strategies.view.list.holder.StrategyListItemView

@Composable()
fun StrategiesListView(
    listState: ListState,
    items: List<CompositeStrategyUiModel>,
    onFetchNext: () -> Unit,
) {
    BaseListView(listState = listState,
                 items = items,
                 onFetchNext = onFetchNext,
                 emptyContent = { EmptyListView() },
                 itemContent = { index ->
                     StrategyListItemView(
                         modifier = Modifier.then(
                             if (index == items.size - 1) {
                                 Modifier.padding(bottom = 18.dp)
                             } else {
                                 Modifier
                             }
                         ),
                         items[index],
                     )
                 })
}

@Composable
private fun EmptyListView() {
    Text(text = stringResource(R.string.strategies_list_is_empty))
}
