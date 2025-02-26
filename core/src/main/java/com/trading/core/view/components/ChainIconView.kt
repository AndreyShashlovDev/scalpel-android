package com.trading.core.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.trading.core.BuildConfig
import com.trading.core.R
import com.trading.core.domain.network.model.api.ChainType
import java.util.Locale


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ChainIconView(modifier: Modifier = Modifier, chain: ChainType, showChainName: Boolean = false) {
    val previewHandler = AsyncImagePreviewHandler {
        val drawable =
            ContextCompat.getDrawable(it.context, R.drawable.ic_chain_placeholder_preview)
        drawable?.asImage() ?: ColorImage(Color.LightGray.toArgb())
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(String.format(BuildConfig.DATA_SOURCE_BLOCKCHAIN_IMG, chain.name))
                    .crossfade(false)
                    .transformations(
                        CircleCropTransformation(),
                    )
                    .build(),
                contentDescription = null
            )
        }
        if (showChainName) {
            AppSingleLineView(modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp),
                              overflow = TextOverflow.Ellipsis,
                              text = chain.name.replaceFirstChar { it.uppercase(Locale.ENGLISH) })
        }
    }
}
