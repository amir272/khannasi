package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.UserBasics

interface UserBasicsService {
    fun getAllUsers(): List<UserBasics>
    fun getUserById(userId: Long): UserBasics?
    fun createUser(userBasics: UserBasics): UserBasics
    fun updateUser(userId: Long, updatedUserBasics: UserBasics): UserBasics?
    fun deleteUser(userId: Long)
    fun getUserByUsername(username: String): UserBasics?
}