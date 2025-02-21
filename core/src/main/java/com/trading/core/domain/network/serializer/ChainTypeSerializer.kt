package com.trading.core.domain.network.serializer

import com.trading.core.domain.network.model.api.ChainType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.jvm.jvmName

object ChainTypeSerializer : KSerializer<ChainType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(ChainType::class.jvmName, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: ChainType) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): ChainType {
        val value = decoder.decodeString()
        val subclasses = ChainType::class.sealedSubclasses

        return subclasses.firstOrNull { it.objectInstance?.value == value }?.objectInstance
            ?: ChainType.Undefined(value)
    }
}
