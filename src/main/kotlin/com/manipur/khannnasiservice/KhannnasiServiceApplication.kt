package com.manipur.khannnasiservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class KhannnasiServiceApplication

fun main(args: Array<String>) {
    runApplication<KhannnasiServiceApplication>(*args)

    @Configuration
    class CorsConfiguration {
        @Bean
        fun corsConfigurer(): WebMvcConfigurer {
            return object : WebMvcConfigurer {
                override fun addCorsMappings(registry: CorsRegistry) {
                    registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                }
            }
        }
    }
}
