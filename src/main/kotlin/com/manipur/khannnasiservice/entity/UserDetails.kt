package com.manipur.khannnasiservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "user_details")
data class UserDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    val userBasics: UserBasics,

    @Column(name = "email", nullable = true, unique = true)
    val email: String,

    @Column(name = "contact_no", nullable = true)
    val contactNo: String,

    @Column(name = "address", nullable = true)
    val address: String,

    @Column(name = "state", nullable = true)
    val state: String,

    @Column(name = "country", nullable = true)
    val country: String,

    @Column(name = "password_hash", nullable = false)
    val passwordHash: String,

    @Column(name = "is_verified", nullable = false)
    val isVerified: Boolean = false,

    @Column(name = "points", nullable = false)
    val points: Long = 0
) {
    constructor() : this(
        email = "",
        userBasics = UserBasics(),
        contactNo = "",
        address = "",
        state = "",
        country = "",
        passwordHash = ""
    )
}