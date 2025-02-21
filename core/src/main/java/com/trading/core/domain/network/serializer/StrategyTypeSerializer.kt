package com.trading.core.domain.network.serializer

import com.trading.core.domain.network.model.api.StrategyType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.jvm.jvmName

object StrategyTypeSerializer : KSerializer<StrategyType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(StrategyType::class.jvmName, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: StrategyType) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): StrategyType {
        val value = decoder.decodeString()
        val subclasses = StrategyType::class.sealedSubclasses

        return subclasses.firstOrNull { it.objectInstance?.value == value }?.objectInstance
            ?: StrategyType.Undefined(value)
    }
}
