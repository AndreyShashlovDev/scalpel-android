package com.trading.core.domain.network.serializer

import com.trading.core.domain.evm.Address
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.jvm.jvmName

object AddressSerializer : KSerializer<Address> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(Address::class.jvmName, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Address) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): Address {
        val value = decoder.decodeString()
        return Address.from(value) ?: throw Exception("wrong address format")
    }
}
