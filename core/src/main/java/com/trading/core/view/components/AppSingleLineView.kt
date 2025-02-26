package com.trading.core.view.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AppSingleLineView(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    text: String,
    size: ComponentSize = ComponentSize.MEDIUM,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        modifier = modifier,
        text = text,
        style = when (size) {
            ComponentSize.SMALL -> MaterialTheme.typography.bodySmall
            ComponentSize.LARGE -> MaterialTheme.typography.bodyLarge
            ComponentSize.MEDIUM -> MaterialTheme.typography.bodyMedium
        },
        maxLines = 1,
        overflow = overflow,
        color = color,
        softWrap = false,
        textAlign = TextAlign.Center
    )
}

@Composable
fun AppSingleLineView(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    size: ComponentSize = ComponentSize.MEDIUM,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        modifier = modifier, text = text, style = when (size) {
            ComponentSize.SMALL -> MaterialTheme.typography.bodySmall
            ComponentSize.LARGE -> MaterialTheme.typography.bodyLarge
            ComponentSize.MEDIUM -> MaterialTheme.typography.bodyMedium
        }, maxLines = 1, overflow = overflow, softWrap = false, textAlign = TextAlign.Center
    )
}
