package com.trading.feature_strategies.presentation.mapper

import com.trading.feature_strategies.domain.model.CompositeStrategy
import com.trading.feature_strategies.domain.model.SimpleHistory
import com.trading.feature_strategies.domain.model.Strategy
import com.trading.feature_strategies.domain.model.StrategyOptions
import com.trading.feature_strategies.presentation.model.CompositeStrategyUiModel
import com.trading.feature_strategies.presentation.model.CurrencyUiModel
import com.trading.feature_strategies.presentation.model.StrategyOptionsUiModel
import com.trading.feature_strategies.presentation.model.StrategyUiModel
import java.math.BigDecimal
import java.math.BigInteger

object CompositeStrategyUiMapper {
    fun CompositeStrategy.uiModel(): CompositeStrategyUiModel {
        return CompositeStrategyUiModel(
            id = strategy.orderHash,
            strategy = strategy.uiModel(swapHistory),
        )
    }

    private fun Strategy.uiModel(history: List<SimpleHistory>): StrategyUiModel {
        val currencyBPrice = currencyB.price?.let { currencyA.valueTo(currencyB.price.usdtPrice) }
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
            approvedA = approvedA,
            approvedB = approvedB,
            currencyBUsdPrice = currencyBPrice,
            totalAmountA = currencyA.valueTo(totalAmountA),
            totalAmountB = currencyB.valueTo(totalAmountB),
            totalUsdAmountB = currencyB.valueTo(totalAmountB)
                .multiply(currencyBPrice ?: BigDecimal.ZERO),
            initialAmountA = initialAmountAValue,
            totalUsdProfit = totalUsdProfit,
            options = options.uiModel(this),
            createdAt = createdAt
        )
    }

    private fun StrategyOptions.uiModel(strategy: Strategy): StrategyOptionsUiModel {
        val hundred = BigDecimal(100)

        return StrategyOptionsUiModel(
            stopLoss = stopLossPercents?.let { BigDecimal(it.toString()).multiply(hundred) },
            gasPriceLimit = strategy.gasLimit.divide(BigInteger("10").pow(9))
                .toInt(),
            growDiffPercentsUp = growDiffPercentsUp?.let {
                BigDecimal(it.toString()).multiply(hundred)
            },
            growDiffPercentsDown = growDiffPercentsDown?.let {
                BigDecimal(it.toString()).multiply(hundred)
            },
            buyMaxPrice = buyMaxPrice?.let { strategy.currencyB.valueTo(it) }
        )
    }
}
