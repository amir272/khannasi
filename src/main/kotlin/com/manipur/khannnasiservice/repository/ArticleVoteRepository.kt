package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.entity.ArticleVote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleVoteRepository : JpaRepository<ArticleVote, Long>