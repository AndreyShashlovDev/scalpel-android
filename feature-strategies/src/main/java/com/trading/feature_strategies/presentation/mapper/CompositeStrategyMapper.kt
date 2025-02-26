package com.trading.feature_strategies.presentation.mapper

import com.trading.feature_strategies.domain.model.CompositeStrategy
import com.trading.feature_strategies.domain.model.SimpleHistory
import com.trading.feature_strategies.domain.model.Strategy
import com.trading.feature_strategies.presentation.model.CompositeStrategyUiModel
import com.trading.feature_strategies.presentation.model.CurrencyUiModel
import com.trading.feature_strategies.presentation.model.StrategyUiModel
import java.math.BigDecimal

object CompositeStrategyUiMapper {
    fun CompositeStrategy.toDomain(): CompositeStrategyUiModel {
        return CompositeStrategyUiModel(
            id = strategy.orderHash,
            strategy = strategy.toDomain(swapHistory),
        )
    }

    private fun Strategy.toDomain(history: List<SimpleHistory>): StrategyUiModel {
        val currencyBPrice =
            if (currencyB.price != null) currencyA.valueTo(currencyB.price.usdtPrice) else null

        val initialAmountAValue = currencyA.valueTo(initialAmountA)

        val totalUsdProfit = history.fold(BigDecimal(0)) { acc, current ->
            acc + (currencyA.valueTo(current.value) - initialAmountAValue) - acc
        }

        return StrategyUiModel(
            chain = chain,
            status = status,
            type = type,
            wallet = wallet,
            currencyA = CurrencyUiModel(
                symbol = currencyA.symbol,
                address = currencyA.address,
            ),
            currencyB = CurrencyUiModel(
                symbol = currencyB.symbol,
                address = currencyB.address,
            ),
            currencyBUsdPrice = currencyBPrice,
            totalAmountA = currencyA.valueTo(totalAmountA),
            totalAmountB = currencyB.valueTo(totalAmountB),
            totalUsdAmountB = currencyB.valueTo(totalAmountB)
                .multiply(currencyBPrice ?: BigDecimal.ZERO),
            initialAmountA = initialAmountAValue,
            totalUsdProfit = totalUsdProfit,
            createdAt = createdAt
        )
    }
}
