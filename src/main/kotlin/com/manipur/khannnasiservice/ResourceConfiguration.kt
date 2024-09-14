package com.manipur.khannnasiservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.concurrent.TimeUnit


@Configuration
class ResourceConfiguration : WebMvcConfigurer {
    @Value("\${image.path.file}")
    private lateinit var imagePathFile: String

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // Configure resource handlers
        registry.addResourceHandler("/profile/images/**")
            .addResourceLocations(imagePathFile)
            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic())
        registry.addResourceHandler("/article/images/**")
            .addResourceLocations(imagePathFile)
            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic())
        registry.addResourceHandler("/discussion/images/**")
            .addResourceLocations(imagePathFile)
            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic())
        registry.addResourceHandler("/comment/images/**")
            .addResourceLocations(imagePathFile)
            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic())
        registry.addResourceHandler("/images/**")
            .addResourceLocations(imagePathFile)
            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic())
    }
}