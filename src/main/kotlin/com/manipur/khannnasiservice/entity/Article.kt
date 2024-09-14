package com.manipur.khannnasiservice.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.jvm.Transient

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
    val publishedDate: LocalDateTime,

    @Column(name = "main_category", nullable = false)
    val mainCategory: String,

    @Column(name = "sub_categories")
    val subCategories: String,

    @Column(name = "language_type", nullable = false)
    val languageType: String,

    @Column(name = "representative_picture")
    val representativePicture: String?,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", insertable = false, updatable = false)
    val comments: List<ArticleComment>? = null,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_post_id", referencedColumnName = "article_id", insertable = false, updatable = false)
    val votes: List<ArticleVote>? = null
) {
    constructor() : this(
        title = "",
        content = "",
        author = UserBasics(),
        publishedDate = LocalDateTime.now(),
        mainCategory = "",
        subCategories = "",
        languageType = "",
        representativePicture = null,
        comments = null,
        votes = null
    )
}