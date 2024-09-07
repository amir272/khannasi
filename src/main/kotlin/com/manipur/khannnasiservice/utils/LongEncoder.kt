package com.manipur.khannnasiservice.utils

object LongEncoder {
    private const val KEY: Long = 0x5A5A5A5A5A5A5A5A

    fun encode(value: Long): Long {
        return value xor KEY
    }

    fun decode(encodedValue: Long): Long {
        return encodedValue xor KEY
    }
}