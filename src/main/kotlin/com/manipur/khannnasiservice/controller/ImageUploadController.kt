package com.manipur.khannnasiservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@CrossOrigin
@RestController
class ImageUploadController {

    @Value("\${image.path}")
    private lateinit var imageFolder: String

    @Value("\${host.type}")
    private lateinit var hostType: String
//    private val imageFolder = "/Users/mac/Desktop/images/"

    @PostMapping("/api/upload-image")
    @Throws(IOException::class)
    fun uploadImage(
        @RequestParam("file") file: MultipartFile,
        @RequestHeader(value = "Origin", required = false) origin: String?
    ): ResponseEntity<Map<String, String>> {
        // Sanitize input

        var filename = file.originalFilename
        if (filename == null || filename.contains("..") || filename.matches("[^\\w\\s\\d\\-_~,;:\\[\\]\\(\\).]+".toRegex())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }

        // Verify extension
        val extension = filename.substring(filename.lastIndexOf(".") + 1)
        if (!mutableListOf("gif", "jpg", "png", "jpeg", "webp").contains(extension.lowercase(Locale.getDefault()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }

        // Save uploaded file
        filename = Date().time.toString() + filename
        val path = Paths.get(imageFolder + filename)
        // Create directory if it does not exist
        Files.createDirectories(path.parent)

        Files.write(path, file.bytes)

        // Determine the base URL
        val response = getBaseUrl(origin, filename, hostType)
        return ResponseEntity.ok(response)
    }

    companion object {

        private fun getBaseUrl(origin: String?, filename: String, hostType: String): Map<String, String> {
            var protocol = "http://"
            if (origin != null && origin.startsWith("https")) {
                protocol = "https://"
            }
            val baseurl = "$protocol$hostType:8082/"

            // Respond to the successful upload with JSON.
            // Use a location key to specify the path to the saved image resource.
            // { location : '/your/uploaded/image/file'}
            val response: MutableMap<String, String> = HashMap()
            response["location"] = "images/$filename"
            return response
        }
    }
}
