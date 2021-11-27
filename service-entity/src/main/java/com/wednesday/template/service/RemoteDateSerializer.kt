package com.wednesday.template.service

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object RemoteDateSerializer : KSerializer<Date> {
    private val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override val descriptor = PrimitiveSerialDescriptor("RemoteDateSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Date {
        return format.parse(decoder.decodeString())!!
    }

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(format.format(value))
    }
}
