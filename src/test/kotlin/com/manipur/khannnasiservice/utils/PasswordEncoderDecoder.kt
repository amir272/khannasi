package com.manipur.khannnasiservice.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.junit.jupiter.api.Test

class PasswordEncoderDecoder {

    @Test
    fun encodePassword(){
        val password = "password"
        val passwordEncoder = BCryptPasswordEncoder()
        val encodedPassword = passwordEncoder.encode(password)
        println(encodedPassword)
    }

    @Test
    fun decodePassword() {
        val password = 1234
        val passwordEncoder = BCryptPasswordEncoder()
        val encodedPassword = passwordEncoder.encode(password.toString())
        val isPasswordMatch = passwordEncoder.matches(password.toString(), encodedPassword)
        println(isPasswordMatch)
    }
}