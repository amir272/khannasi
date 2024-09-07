package com.manipur.khannnasiservice.repository

import com.manipur.khannnasiservice.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long>