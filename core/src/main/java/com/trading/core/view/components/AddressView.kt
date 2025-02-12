package com.trading.core.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.trading.core.view.theme.Blue
import com.trading.core.utility.evm.Address

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
