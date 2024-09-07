package com.manipur.khannnasiservice.controller

import com.manipur.khannnasiservice.entity.UserBasics
import com.manipur.khannnasiservice.service.UserBasicsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user/basics")
class UserBasicsController(@Autowired private val userBasicsService: UserBasicsService) {

    @GetMapping
    fun getAllUsers(): List<UserBasics> = userBasicsService.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserBasics> {
        val user = userBasicsService.getUserById(id)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(@RequestBody userBasics: UserBasics): UserBasics = userBasicsService.createUser(userBasics)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody userBasics: UserBasics): ResponseEntity<UserBasics> {
        val updatedUser = userBasicsService.updateUser(id, userBasics)
        return if (updatedUser != null) {
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        userBasicsService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }
}