package com.trading.core.domain.network.serializer

import com.trading.core.domain.evm.TransactionHash
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.jvm.jvmName

object TransactionHashSerializer : KSerializer<TransactionHash> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(TransactionHash::class.jvmName, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: TransactionHash) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): TransactionHash {
        val value = decoder.decodeString()
        return TransactionHash.from(value) ?: throw Exception("wrong transaction hash format")
    }
}
