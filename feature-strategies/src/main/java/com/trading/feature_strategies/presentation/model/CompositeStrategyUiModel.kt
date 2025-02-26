package com.trading.feature_strategies.presentation.model

import com.trading.core.view.components.ListItem

data class CompositeStrategyUiModel(
    override val id: String, val strategy: StrategyUiModel
) : ListItem<String>
