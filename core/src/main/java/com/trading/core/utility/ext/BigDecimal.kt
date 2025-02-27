package com.trading.core.utility.ext

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun BigDecimal.toCurrencyFormat(decimals: Int = 2, prefix: Boolean = true): String {
    if (prefix) {
        val formatter = NumberFormat.getCurrencyInstance(Locale.ENGLISH)
        formatter.minimumFractionDigits = 0
        formatter.maximumFractionDigits = decimals
        formatter.currency = Currency.getInstance("USD")

        return formatter.format(this)

    } else {
        return this.setScale(decimals, RoundingMode.HALF_EVEN)
            .stripTrailingZeros()
            .toPlainString()
    }
}
