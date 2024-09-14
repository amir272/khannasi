package com.manipur.khannnasiservice.dto

import com.manipur.khannnasiservice.entity.Article
import com.manipur.khannnasiservice.entity.ArticleComment
import com.manipur.khannnasiservice.entity.ArticleVote

data class UserDTO(
    val userId: Long,
    val username: String,
    val profilePictureUrl: String?
)

data class ArticleCommentsVotes(
    val article: Article?,
    val articleComment: ArticleComment?,
    val articleVotes: ArticleVote?
) {
    constructor() : this(
        article = null,
        articleComment = null,
        articleVotes = null
    )
}