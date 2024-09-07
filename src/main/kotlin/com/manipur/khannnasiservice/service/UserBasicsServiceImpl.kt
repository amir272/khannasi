package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.UserBasics
import com.manipur.khannnasiservice.repository.UserBasicsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserBasicsServiceImpl(@Autowired private val userBasicsRepository: UserBasicsRepository) : UserBasicsService {

    override fun getAllUsers(): List<UserBasics> = userBasicsRepository.findAll()

    override fun getUserById(userId: Long): UserBasics? = userBasicsRepository.findById(userId).orElse(null)

    override fun createUser(userBasics: UserBasics): UserBasics = userBasicsRepository.save(userBasics)

    override fun updateUser(userId: Long, updatedUserBasics: UserBasics): UserBasics? {
        return if (userBasicsRepository.existsById(userId)) {
            userBasicsRepository.save(updatedUserBasics)
        } else {
            null
        }
    }

    override fun deleteUser(userId: Long) {
        if (userBasicsRepository.existsById(userId)) {
            userBasicsRepository.deleteById(userId)
        }
    }
}