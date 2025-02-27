package com.trading.core.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppButtonView(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: ComponentSize = ComponentSize.MEDIUM
) {
    val maxHeight = when (size) {
        ComponentSize.MEDIUM -> 28.dp
        ComponentSize.SMALL -> 24.dp
        else -> 48.dp
    }

    val contentPadding =
        if (size === ComponentSize.SMALL) PaddingValues(0.dp) else ButtonDefaults.ContentPadding

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        contentPadding = contentPadding,
        modifier = modifier
            .widthIn(
                min = 80.dp, max = 300.dp
            )
            .fillMaxWidth()
            .heightIn(maxHeight),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.primary
        )
    ) {
        AppSingleLineView(text = text, size = size)
    }
}
