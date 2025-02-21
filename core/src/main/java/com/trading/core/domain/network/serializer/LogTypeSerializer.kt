package com.trading.core.domain.network.serializer

import com.trading.core.domain.network.model.api.LogType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.jvm.jvmName

object LogTypeSerializer : KSerializer<LogType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(LogType::class.jvmName, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LogType) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): LogType {
        val value = decoder.decodeString()
        val subclasses = LogType::class.sealedSubclasses

        return subclasses.firstOrNull { it.objectInstance?.value == value }?.objectInstance
            ?: LogType.Undefined(value)
    }
}
