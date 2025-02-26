package com.trading.feature_strategies.view.list.holder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.trading.core.domain.evm.Address
import com.trading.core.domain.network.model.api.ChainType
import com.trading.core.domain.network.model.api.StrategyStatusType
import com.trading.core.domain.network.model.api.StrategyType
import com.trading.core.utility.ext.toCurrencyFormat
import com.trading.core.view.components.AppSingleLineView
import com.trading.core.view.components.ChainIconView
import com.trading.core.view.components.ComponentSize
import com.trading.core.view.components.TokenIconView
import com.trading.core.view.components.ext.bottomBorder
import com.trading.core.view.preview.CompletePreview
import com.trading.core.view.theme.ExtendedTheme
import com.trading.core.view.theme.ScalpelTheme
import com.trading.feature_orders.R
import com.trading.feature_strategies.presentation.model.CompositeStrategyUiModel
import com.trading.feature_strategies.presentation.model.CurrencyUiModel
import com.trading.feature_strategies.presentation.model.StrategyUiModel
import java.math.BigDecimal
import java.util.Date
import java.util.Locale

private val DefaultModel = CompositeStrategyUiModel(
    id = "0x7ceB23fD6bC0adD59E62ac25578270cFf1b9f619", strategy = StrategyUiModel(
        chain = ChainType.Ethereum,
        status = StrategyStatusType.Created,
        type = StrategyType.ClassicScalpel,
        wallet = Address.from("0x7ceB23fD6bC0adD59E62ac25578270cFf1b9f619")!!,
        currencyA = CurrencyUiModel(
            "ETH",
            Address.from("0xffffffffffffffffffffffffffffffffffffffff")!!,
        ),
        currencyB = CurrencyUiModel(
            "UNI",
            Address.from("0xb33EaAd8d922B1083446DC23f610c2567fB5180f")!!,
        ),
        currencyBUsdPrice = BigDecimal(0.6),
        totalAmountA = BigDecimal(1000),
        totalAmountB = BigDecimal(1),
        totalUsdAmountB = BigDecimal(0.4),
        initialAmountA = BigDecimal(1000),
        totalUsdProfit = BigDecimal(140),
        createdAt = Date()

    )
)

@Composable
@CompletePreview
fun StrategyListItemView(
    modifier: Modifier = Modifier, item: CompositeStrategyUiModel = DefaultModel
) {
    val strategy = item.strategy

    ScalpelTheme(
        darkTheme = true
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                TitleBlock(strategy)

                Spacer(Modifier.height(10.dp))

                ValueLineView(stringResource(R.string.strategies_list_item_status)) {
                    StatusView(strategy.status)
                }

                ValueLineView(
                    String.format(
                        Locale.ENGLISH,
                        "%s Price: %s",
                        strategy.currencyB.symbol,
                        strategy.currencyBUsdPrice?.toCurrencyFormat()
                    )
                ) {}

                ValueLineView(stringResource(R.string.strategies_list_item_usd_amount)) {
                    UsdAmountValueView(strategy)
                }

                ValueLineView(stringResource(R.string.strategies_list_item_current_profit)) {
                    CurrentProfitValueView(strategy)
                }

                ValueLineView(stringResource(R.string.strategies_list_item_earned_profit)) {
                    AppSingleLineView(
                        modifier = Modifier.padding(end = 8.dp),
                        text = strategy.totalUsdProfit.toCurrencyFormat(),
                        color = getColorByValue(strategy.totalUsdProfit),
                        size = ComponentSize.SMALL
                    )
                }
            }
        }
    }
}

@Composable
private fun TitleBlock(strategy: StrategyUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .bottomBorder()
            .padding(bottom = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        ChainIconView(chain = strategy.chain)
        TokenIconView(
            modifier = Modifier.padding(start = 12.dp),
            chain = strategy.chain,
            address = strategy.currencyA.address
        )
        TokenIconView(
            chain = strategy.chain, address = strategy.currencyB.address
        )
        AppSingleLineView(
            modifier = Modifier.padding(start = 12.dp),
            text = String.format(
                Locale.ENGLISH, "%s→%s", strategy.currencyA.symbol, strategy.currencyB.symbol
            ),
            size = ComponentSize.SMALL,
        )

        AppSingleLineView(
            modifier = Modifier.weight(1.0f),
            text = String.format(
                Locale.ENGLISH,
                "%s / %s",
                strategy.totalAmountA.toCurrencyFormat(),
                strategy.totalAmountB.toCurrencyFormat()
            ),
            size = ComponentSize.SMALL,
        )
        IconButton(modifier = Modifier.size(24.dp), onClick = {}) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "open")
        }
    }
}

@Composable
private fun StatusView(status: StrategyStatusType) {
    val color = when (status) {
        is StrategyStatusType.InProgress, is StrategyStatusType.ApproveInProgress -> ExtendedTheme.colors.success
        is StrategyStatusType.Canceled, is StrategyStatusType.UserActionRequired -> MaterialTheme.colorScheme.error
        is StrategyStatusType.Paused, is StrategyStatusType.Created -> ExtendedTheme.colors.warning
        else -> MaterialTheme.colorScheme.secondary
    }
    AppSingleLineView(
        text = stringResource(status.resId), color = color, size = ComponentSize.SMALL
    )
}

@Composable
private fun getColorByValue(value: BigDecimal): Color {
    return when {
        value > BigDecimal.ZERO -> ExtendedTheme.colors.success
        value < BigDecimal.ZERO -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }
}

@Composable
private fun ValueLineView(
    text: String,
    value: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
    ) {
        AppSingleLineView(
            modifier = Modifier.padding(end = 8.dp), text = text, size = ComponentSize.SMALL
        )

        value()
    }
    Spacer(Modifier.height(4.dp))
}

@Composable
private fun PositiveOrNegativeView(value: BigDecimal) {
    AppSingleLineView(
        modifier = Modifier.padding(end = 8.dp),
        text = value.toCurrencyFormat(),
        color = getColorByValue(value),
        size = ComponentSize.SMALL
    )
}


@Composable
private fun UsdAmountValueView(strategy: StrategyUiModel) {
    val value =
        (if (strategy.totalUsdAmountB > BigDecimal.ZERO) strategy.totalUsdAmountB else strategy.totalAmountA) - strategy.initialAmountA

    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = getColorByValue(value))) {
            append(
                (if (strategy.totalUsdAmountB > BigDecimal.ZERO) strategy.totalUsdAmountB else strategy.totalAmountA).toCurrencyFormat()
            )
        }
        append(" / ")
        append(strategy.initialAmountA.toCurrencyFormat())
    }

    AppSingleLineView(
        modifier = Modifier.padding(end = 8.dp), text = annotatedText, size = ComponentSize.SMALL
    )
}

@Composable
private fun CurrentProfitValueView(strategy: StrategyUiModel) {
    val value =
        (if (strategy.totalUsdAmountB > BigDecimal.ZERO) strategy.totalUsdAmountB else strategy.totalAmountA) - strategy.initialAmountA

    AppSingleLineView(
        modifier = Modifier.padding(end = 8.dp),
        text = ((if (strategy.totalUsdAmountB > BigDecimal.ZERO) strategy.totalUsdAmountB else strategy.totalAmountA) - strategy.initialAmountA).toCurrencyFormat(),
        color = getColorByValue(value),
        size = ComponentSize.SMALL
    )
}
