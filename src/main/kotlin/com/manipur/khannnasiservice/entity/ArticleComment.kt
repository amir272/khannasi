package com.manipur.khannnasiservice.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.manipur.khannnasiservice.dto.UserDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import java.time.LocalDateTime

@Entity
@Table(name = "article_comments")
data class ArticleComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val commentId: Long = 0,

    @Column(name = "article_id", nullable = true)
    val articleId: Long,


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val userBasics: UserBasics,

    @Column(name = "content", nullable = false, length = 100000)
    val content: String,

    @Column(name = "timestamp", nullable = false)
    val timestamp: LocalDateTime,

    @Column(name = "reply_to", nullable = false)
    val replyTo: Long,

    @Column(name = "deleted", nullable = false)
    val deleted: Boolean = false
) {
    @get:JsonProperty("user")
    val userDTO: UserDTO
        get() = UserDTO(userBasics.userId, userBasics.username, userBasics.profilePictureUrl)

    constructor() : this(
        articleId = 0,
        userBasics = UserBasics(),
        content = "",
        timestamp = LocalDateTime.now(),
        replyTo = 0,
        deleted = false
    )
}