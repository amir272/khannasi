package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.entity.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDetailsRepository : JpaRepository<UserDetails, Long> {
}