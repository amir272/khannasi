package com.manipur.khannnasiservice.utils

import java.util.Base64

object Base64Util {
    fun <T> encodeToBase64(value: T): String {
        val encodedBytes = Base64.getUrlEncoder().withoutPadding().encode(value.toString().toByteArray())
        return encodedBytes.joinToString("") { it.toInt().toString(16).padStart(2, '0') }
    }

    fun <T> decodeFromBase64(base64String: String, converter: (String) -> T): T {
        val bytes = base64String.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
        val decodedString = String(Base64.getUrlDecoder().decode(bytes))
        return converter(decodedString)
    }
}