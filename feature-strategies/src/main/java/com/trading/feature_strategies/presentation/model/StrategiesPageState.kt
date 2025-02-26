package com.trading.feature_strategies.presentation.model

import com.trading.core.view.components.ListState

data class StrategiesPageState(
    val listState: ListState = ListState.Loading,
    val items: List<CompositeStrategyUiModel> = emptyList()
)
