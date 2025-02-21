package com.trading.core.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.trading.core.domain.evm.Address
import com.trading.core.view.theme.Blue

@Composable
fun AddressView(
    address: Address,
    size: ComponentSize = ComponentSize.MEDIUM,
) {
    AppSingleLineView(
        color = Blue,
        size = size,
        text = address.shorten(),
        overflow = TextOverflow.Visible,
    )
}
