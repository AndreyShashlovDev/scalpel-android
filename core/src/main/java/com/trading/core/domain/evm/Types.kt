package com.trading.core.domain.evm

import com.trading.core.domain.network.serializer.AddressSerializer
import com.trading.core.domain.network.serializer.TransactionHashSerializer
import kotlinx.serialization.Serializable

@JvmInline
@Serializable(with = AddressSerializer::class)
value class Address private constructor(val value: String) {

    fun shorten(): String {
        return value.substring(
            0, 10
        ) + "..." + value.substring(32)
    }

    companion object {
        fun from(value: String?): Address? {
            return value?.let {
                if (it.startsWith("0x")) {
                    return Address(it)
                }

                return null
            }
        }
    }
}

@JvmInline
@Serializable(with = TransactionHashSerializer::class)
value class TransactionHash private constructor(val value: String) {

    fun shorten(): String {
        return value.substring(
            0, 14
        ) + "..." + value.substring(52)
    }

    companion object {
        fun from(value: String?): TransactionHash? {
            return value?.let {
                if (it.startsWith("0x")) {
                    return TransactionHash(it)
                }

                return null
            }
        }
    }
}

@JvmInline
value class HexNumber private constructor(val value: String) {
    companion object {
        fun from(value: String?): HexNumber? {
            return value?.let {
                if (it.startsWith("0x")) {
                    return HexNumber(it)
                }

                return null
            }
        }
    }
}
