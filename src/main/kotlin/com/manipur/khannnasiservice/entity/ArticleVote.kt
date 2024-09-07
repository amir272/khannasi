package com.manipur.khannnasiservice.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn

@Entity
@Table(name = "article_votes")
data class ArticleVote(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    val voteId: Long = 0,


    @Column(name = "comment_id", nullable = true)
    val commentId: Long,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    val userBasics: UserBasics,

    @Column(name = "vote_type", nullable = false)
    val voteType: String,

    @Column(name = "original_post_id", nullable = false)
    val originalPostId: Long
) {
    constructor() : this(
        commentId = 0,
        userBasics = UserBasics(),
        voteType = "",
        originalPostId = 0
    )
}