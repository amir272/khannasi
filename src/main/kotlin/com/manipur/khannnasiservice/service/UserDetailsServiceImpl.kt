package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.UserBasics
import com.manipur.khannnasiservice.entity.UserDetails
import com.manipur.khannnasiservice.repository.UserBasicsRepository
import com.manipur.khannnasiservice.repository.UserDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserDetailsServiceImpl(
    @Autowired private val userDetailsRepository: UserDetailsRepository,
    @Autowired private val userBasicsRepository: UserBasicsRepository,
    @Autowired private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun getAllUsers(): List<UserDetails> = userDetailsRepository.findAll()

    override fun getUserById(userId: Long): UserDetails? = userDetailsRepository.findById(userId).orElse(null)

    override fun createUser(userDetails: UserDetails): UserDetails {
        val userBasics = userDetails.userBasics
        val existingUserBasics = userBasicsRepository.findById(userBasics.userId).orElse(null)

        if (existingUserBasics == null) {
            if (userBasics.username.isEmpty() || (userBasicsRepository.findByUsername(userBasics.username) != null)
            ) {
                throw IllegalArgumentException("Invalid or existing username")
            }
            val newUserBasics = userBasicsRepository.save(userBasics)
            val hashedPassword = passwordEncoder.encode(userDetails.passwordHash)
            return userDetailsRepository.save(userDetails.copy(userBasics = newUserBasics, passwordHash = hashedPassword))
        }

        val hashedPassword = passwordEncoder.encode(userDetails.passwordHash)
        return userDetailsRepository.save(userDetails.copy(userBasics = existingUserBasics, passwordHash = hashedPassword))
    }

    override fun updateUser(userId: Long, updatedUserDetails: UserDetails): UserDetails? {
        val existingUserDetails = userDetailsRepository.findById(userId).orElse(null)
        return if (existingUserDetails != null) {
            Logger.getLogger("UserDetailsServiceImpl").info("User ID: $userId To Update: $updatedUserDetails")

            val hashedPassword = if(updatedUserDetails.passwordHash != existingUserDetails.passwordHash) {
                passwordEncoder.encode(updatedUserDetails.passwordHash)
            } else {
                updatedUserDetails.passwordHash
            }
            userDetailsRepository.save(updatedUserDetails.copy(passwordHash = hashedPassword))
        } else {
            null
        }
    }

    override fun deleteUser(userId: Long) {
        if (userDetailsRepository.existsById(userId)) {
            userDetailsRepository.deleteById(userId)
        }
    }

    override fun findByUsernameAndPassword(username: String, password: String): UserDetails? {
        val userBasics = userBasicsRepository.findByUsername(username)
        return if (userBasics == null) {
            null
        } else {
            val userDetails = userDetailsRepository.findUserBasicsUserId(userBasics.userId)
            Logger.getLogger("UserDetailsServiceImpl").info("Username: $username, Password: ${userDetails?.passwordHash}")
            if (userDetails != null && passwordEncoder.matches(password, userDetails.passwordHash)) {
                userDetails
            } else {
                null
            }
        }
    }
}