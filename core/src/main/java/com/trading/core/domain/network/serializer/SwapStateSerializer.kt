package com.trading.core.domain.network.serializer

import com.trading.core.domain.network.model.api.SwapState
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.jvm.jvmName

object SwapStateSerializer : KSerializer<SwapState> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(SwapState::class.jvmName, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: SwapState) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): SwapState {
        val value = decoder.decodeString()
        val subclasses = SwapState::class.sealedSubclasses

        return subclasses.firstOrNull { it.objectInstance?.value == value }?.objectInstance
            ?: SwapState.Undefined(value)
    }
}
