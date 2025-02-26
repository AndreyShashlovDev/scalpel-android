package com.trading.core.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
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
import com.trading.core.domain.evm.Address
import com.trading.core.domain.network.model.api.ChainType
import java.util.Locale

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TokenIconView(
    modifier: Modifier = Modifier, chain: ChainType, address: Address
) {
    val url =
        if (address.value.lowercase(Locale.ENGLISH) === "0xffffffffffffffffffffffffffffffffffffffff") {
            String.format(BuildConfig.DATA_SOURCE_NATIVE_COIN_IMG, chain.name)
        } else {
            String.format(BuildConfig.DATA_SOURCE_TOKEN_IMG, chain.name, address.value)
        }

    val previewHandler = AsyncImagePreviewHandler {
        val drawable =
            ContextCompat.getDrawable(it.context, R.drawable.ic_token_placeholder_preview)
        drawable?.asImage() ?: ColorImage(Color.LightGray.toArgb())
    }

    Box(
        modifier = modifier.background(Color.Transparent)
    ) {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .crossfade(false)
                    .transformations(
                        CircleCropTransformation(),
                    )
                    .build(),
                contentDescription = null
            )
        }
    }
}
