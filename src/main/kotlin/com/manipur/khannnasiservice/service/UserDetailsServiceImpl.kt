package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.UserBasics
import com.manipur.khannnasiservice.entity.UserDetails
import com.manipur.khannnasiservice.repository.UserBasicsRepository
import com.manipur.khannnasiservice.repository.UserDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    @Autowired private val userDetailsRepository: UserDetailsRepository,
    @Autowired private val userBasicsRepository: UserBasicsRepository
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
            return userDetailsRepository.save(userDetails.copy(userBasics = newUserBasics))
        }

        return userDetailsRepository.save(userDetails.copy(userBasics = existingUserBasics))
    }

    override fun updateUser(userId: Long, updatedUserDetails: UserDetails): UserDetails? {
        return if (userDetailsRepository.existsById(userId)) {
            userDetailsRepository.save(updatedUserDetails)
        } else {
            null
        }
    }

    override fun deleteUser(userId: Long) {
        if (userDetailsRepository.existsById(userId)) {
            userDetailsRepository.deleteById(userId)
        }
    }
}