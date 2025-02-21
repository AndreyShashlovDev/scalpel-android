package com.trading.core.domain.network.serializer

import com.trading.core.domain.network.model.api.StrategyStatusType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.jvm.jvmName

object StrategyStatusTypeSerializer : KSerializer<StrategyStatusType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(StrategyStatusType::class.jvmName, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: StrategyStatusType) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): StrategyStatusType {
        val value = decoder.decodeString()
        val subclasses = StrategyStatusType::class.sealedSubclasses

        return subclasses.firstOrNull { it.objectInstance?.value == value }?.objectInstance
            ?: StrategyStatusType.Undefined(value)
    }
}
