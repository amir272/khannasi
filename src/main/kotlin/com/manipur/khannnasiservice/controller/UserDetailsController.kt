package com.manipur.khannnasiservice.controller

import com.manipur.khannnasiservice.entity.UserDetails
import com.manipur.khannnasiservice.service.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user-details")
class UserDetailsController(
    @Autowired private val userDetailsService: UserDetailsService
) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDetails>> {
        return ResponseEntity.ok(userDetailsService.getAllUsers())
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Long): ResponseEntity<UserDetails?> {
        val userDetails = userDetailsService.getUserById(userId)
        return if (userDetails != null) {
            ResponseEntity.ok(userDetails)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(@RequestBody userDetails: UserDetails): ResponseEntity<UserDetails> {
        return try {
            ResponseEntity.ok(userDetailsService.createUser(userDetails))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: Long, @RequestBody updatedUserDetails: UserDetails): ResponseEntity<UserDetails?> {
        val updatedUser = userDetailsService.updateUser(userId, updatedUserDetails)
        return if (updatedUser != null) {
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<Void> {
        userDetailsService.deleteUser(userId)
        return ResponseEntity.noContent().build()
    }
}