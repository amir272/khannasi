package com.manipur.khannnasiservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_basics")
data class UserBasics(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val userId: Long = 0,

    @Column(name = "first_name", nullable = false)
    val firstName: String,


    @Column(name = "last_name", nullable = true)
    val lastName: String,

    @Column(name = "username", nullable = false, unique = true)
    val username: String,

    @Column(name = "profile_picture_url")
    val profilePictureUrl: String?
) {
    constructor() : this(
        firstName = "",
        lastName = "",
        username = "",
        profilePictureUrl = null
    )
}