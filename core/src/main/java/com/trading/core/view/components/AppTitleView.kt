package com.trading.core.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.dp
import com.trading.core.view.theme.Gold
import com.trading.core.view.theme.Typography

@Composable
fun AppTitleView(
    text: String,
    size: ComponentSize = ComponentSize.MEDIUM,
) {
    val style = when (size) {
        ComponentSize.SMALL -> Typography.titleSmall
        ComponentSize.LARGE -> Typography.titleLarge
        else -> Typography.titleMedium
    }

    Box(modifier = Modifier) {
        Text(
            text = text,
            color = Gold, //
            modifier = Modifier.offset(y = 5.dp),
            style = style.copy(
                shadow = Shadow(
                    color = Color(
                        249f,
                        164f,
                        0f,
                        0.6f
                    ),
                    offset = Offset(
                        0f,
                        15f
                    ),
                    blurRadius = 15f
                )
            )
        )
    }
}
