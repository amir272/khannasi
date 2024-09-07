package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.entity.ArticleComment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleCommentRepository : JpaRepository<ArticleComment, Long>