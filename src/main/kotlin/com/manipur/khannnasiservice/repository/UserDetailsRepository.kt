package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.entity.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserDetailsRepository : JpaRepository<UserDetails, Long> {

    @Query("SELECT u FROM UserDetails u WHERE u.userBasics.userId = :basicsUserId AND u.passwordHash = :password")
    fun findUserBasicsUserIdAndPassword(basicsUserId: Long, password: String): UserDetails?
}