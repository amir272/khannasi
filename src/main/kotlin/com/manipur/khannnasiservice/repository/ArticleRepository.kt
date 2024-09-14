package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.dto.ArticleCommentsVotes
import com.manipur.khannnasiservice.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long> {

    @Query("SELECT new com.manipur.khannnasiservice.dto.ArticleCommentsVotes(a, c, v) " +
            "FROM Article a " +
            "LEFT JOIN ArticleComment c ON a.articleId = c.articleId " +
            "LEFT JOIN ArticleVote v ON v.voteId = c.articleId " +
            "WHERE a.articleId = :articleId")
    fun getArticleCommentsVotes(@Param("articleId") articleId: Long): ArticleCommentsVotes
}