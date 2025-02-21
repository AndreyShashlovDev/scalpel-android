package com.trading.feature_strategies.data.mapper

import com.trading.feature_strategies.data.model.CompositeStrategyResponse
import com.trading.feature_strategies.data.model.CurrencyResponse
import com.trading.feature_strategies.data.model.LogResponse
import com.trading.feature_strategies.data.model.SimpleHistoryResponse
import com.trading.feature_strategies.data.model.StrategyOptionsResponse
import com.trading.feature_strategies.data.model.StrategyResponse
import com.trading.feature_strategies.data.model.SwapResponse
import com.trading.feature_strategies.domain.model.CompositeStrategy
import com.trading.feature_strategies.domain.model.Currency
import com.trading.feature_strategies.domain.model.Log
import com.trading.feature_strategies.domain.model.SimpleHistory
import com.trading.feature_strategies.domain.model.Strategy
import com.trading.feature_strategies.domain.model.StrategyOptions
import com.trading.feature_strategies.domain.model.Swap

object CompositeStrategyMapper {
    fun CompositeStrategyResponse.toDomain(): CompositeStrategy {
        return CompositeStrategy(strategy = strategy.toDomain(),
                                 swaps = swaps.map { it.toDomain() },
                                 latestLog = latestLog?.toDomain(),
                                 swapHistory = swapHistory.map { it.toDomain() })
    }

    private fun StrategyResponse.toDomain(): Strategy {
        return Strategy(
            chain = chain,
            type = type,
            orderHash = orderHash,
            wallet = wallet,
            currencyA = currencyA.toDomain(),
            currencyB = currencyB.toDomain(),
            totalAmountA = totalAmountA,
            totalAmountB = totalAmountB,
            adaptiveUsdPrice = adaptiveUsdPrice,
            options = options.toDomain(),
            initialAmountA = initialAmountA,
            approvedA = approvedA,
            approvedB = approvedB,
            status = status,
            gasLimit = gasLimit,
            createdAt = createdAt
        )
    }

    private fun SwapResponse.toDomain(): Swap {
        return Swap(
            id = id,
            chain = chain,
            currencyFrom = currencyFrom,
            currencyTo = currencyTo,
            valueFrom = valueFrom,
            valueTo = valueTo,
            exchangeUsdPrice = exchangeUsdPrice,
            profit = profit,
            scalpelFeeAmount = scalpelFeeAmount,
            accumulatorFeeAmount = accumulatorFeeAmount,
            txHash = txHash,
            txFee = txFee,
            state = state,
            updateAt = updateAt
        )
    }

    private fun StrategyOptionsResponse.toDomain(): StrategyOptions {
        return StrategyOptions(
            stopLoss = stopLoss
        )
    }

    private fun LogResponse.toDomain(): Log {
        return Log(
            id = id, type = type,
//            log = log,
            createdAt = createdAt
        )
    }

    private fun SimpleHistoryResponse.toDomain(): SimpleHistory {
        return SimpleHistory(
            date = date, value = value
        )
    }

    private fun CurrencyResponse.toDomain(): Currency {
        return Currency(
            symbol = symbol,
            name = name,
            address = address,
            chain = chain,
            decimal = decimal,
            isStable = isStable,
//            price = price?.toDomain()
        )
    }
}