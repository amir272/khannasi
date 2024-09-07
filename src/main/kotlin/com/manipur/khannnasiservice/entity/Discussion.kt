package com.manipur.khannnasiservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.ElementCollection
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import java.time.LocalDate

@Entity
@Table(name = "discussions")
data class Discussion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discussion_id")
    val discussionId: Long = 0,

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "description", nullable = false, length = 100000)
    val description: String,

    @ManyToOne
    @JoinColumn(name = "initiator_id", nullable = false)
    val initiator: UserBasics,

    @Column(name = "creation_date", nullable = false)
    val creationDate: LocalDate,

    @Column(name = "approval_count", nullable = false)
    val approvalCount: Int = 0,

    @Column(name = "main_category", nullable = false)
    val mainCategory: String,

    @ElementCollection
    @Column(name = "sub_categories")
    val subCategories: MutableList<String> = mutableListOf(),

    @Column(name = "language_type", nullable = false)
    val languageType: String,

    @Column(name = "representative_picture")
    val representativePicture: String? = null
) {
    constructor() : this(
        title = "",
        description = "",
        initiator = UserBasics(),
        creationDate = LocalDate.now(),
        mainCategory = "",
        subCategories = mutableListOf(),
        languageType = "",
        representativePicture = null
    )
}