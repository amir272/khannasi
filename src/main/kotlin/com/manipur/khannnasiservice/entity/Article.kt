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
@Table(name = "articles")
data class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    val articleId: Long = 0,

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "content", nullable = false, length = 100000)
    val content: String,

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    val author: UserBasics,

    @Column(name = "published_date", nullable = false)
    val publishedDate: LocalDate,

    @Column(name = "main_category", nullable = false)
    val mainCategory: String,

    @ElementCollection
    @Column(name = "sub_categories")
    val subCategories: List<String>,

    @Column(name = "language_type", nullable = false)
    val languageType: String,

    @Column(name = "representative_picture")
    val representativePicture: String?
) {
    constructor() : this(
        title = "",
        content = "",
        author = UserBasics(),
        publishedDate = LocalDate.now(),
        mainCategory = "",
        subCategories = emptyList(),
        languageType = "",
        representativePicture = null
    )
}