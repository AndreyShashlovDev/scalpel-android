package com.trading.core.utility.ext

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun BigDecimal.toCurrencyFormat(decimals: Int = 2): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.ENGLISH)
    formatter.minimumFractionDigits = 0
    formatter.maximumFractionDigits = decimals
    formatter.currency = Currency.getInstance("USD")

    return formatter.format(this)
}