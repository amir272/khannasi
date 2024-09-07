package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.entity.UserBasics
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserBasicsRepository : JpaRepository<UserBasics, Long> {
    fun findByUsername(username: String): UserBasics?
}