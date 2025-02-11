package com.trading.core.utility

inline fun <reified T : Any> tag(currentClass: T): String {
    return ("Wallet" + currentClass::class.java.canonicalName!!.substringAfterLast(".")).take(23)
}
