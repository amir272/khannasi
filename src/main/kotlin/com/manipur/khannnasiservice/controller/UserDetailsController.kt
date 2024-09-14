package com.manipur.khannnasiservice.controller

import com.manipur.khannnasiservice.entity.UserDetails
import com.manipur.khannnasiservice.service.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.logging.Logger

@RestController
@RequestMapping("/api/user-details")
class UserDetailsController(
    @Autowired private val userDetailsService: UserDetailsService
) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDetails>> {
        Logger.getLogger("UserDetailsController").info("Get All Users")
        return ResponseEntity.ok(userDetailsService.getAllUsers())
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Long): ResponseEntity<UserDetails?> {
        Logger.getLogger("UserDetailsController").info("User ID: $userId")
        val userDetails = userDetailsService.getUserById(userId)
        return if (userDetails != null) {
            ResponseEntity.ok(userDetails)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{username}/{password}")
    fun getUserByUsernameAndPassword(@PathVariable username: String, @PathVariable password: String): ResponseEntity<UserDetails?> {
        Logger.getLogger("UserDetailsController").info("Username: $username, Password: $password")
        val userDetails = userDetailsService.findByUsernameAndPassword(username, password)
        return if (userDetails != null) {
            ResponseEntity.ok(userDetails)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(@RequestBody userDetails: UserDetails): ResponseEntity<UserDetails> {
        Logger.getLogger("UserDetailsController").info("UserDetails: $userDetails")
        return try {
            ResponseEntity.ok(userDetailsService.createUser(userDetails))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: Long, @RequestBody updatedUserDetails: UserDetails): ResponseEntity<UserDetails?> {
        Logger.getLogger("UserDetailsController").info("To Update UserDetails: $updatedUserDetails")
        val updatedUser = userDetailsService.updateUser(userId, updatedUserDetails)
        return if (updatedUser != null) {
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<Void> {
        Logger.getLogger("UserDetailsController").info("To Delete User with ID: $userId")
        userDetailsService.deleteUser(userId)
        return ResponseEntity.noContent().build()
    }
}