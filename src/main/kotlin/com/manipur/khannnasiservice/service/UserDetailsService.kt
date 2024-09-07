package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.UserDetails

interface UserDetailsService {
    fun getAllUsers(): List<UserDetails>
    fun getUserById(userId: Long): UserDetails?
    fun createUser(userDetails: UserDetails): UserDetails
    fun updateUser(userId: Long, updatedUserDetails: UserDetails): UserDetails?
    fun deleteUser(userId: Long)
    fun findByUsernameAndPassword(username: String, password: String): UserDetails?
}