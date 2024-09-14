package com.manipur.khannnasiservice.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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
@Table(name = "discussion_comments")
data class DiscussionComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val commentId: Long = 0,

    @Column(name = "discussion_id", nullable = false)
    val discussionId: Long,

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
    constructor() : this(
        discussionId = 0,
        userBasics = UserBasics(),
        content = "",
        timestamp = LocalDateTime.now(),
        replyTo = 0,
        deleted = false
    )
}