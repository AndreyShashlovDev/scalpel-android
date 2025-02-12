package com.trading.feature_orders.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.trading.core.view.components.AppSingleLineView
import com.trading.core.view.components.PageLayoutView
import com.trading.feature_orders.presentation.OrdersPagePresenter

@Composable
fun OrdersPage() {
    val presenter: OrdersPagePresenter = hiltViewModel()
//    val state by presenter.state.collectAsState()

    PageLayoutView {
        AppSingleLineView(text = "Orders page")
    }
}
