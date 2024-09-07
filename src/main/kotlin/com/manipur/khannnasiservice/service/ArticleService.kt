package com.manipur.khannnasiservice.service

import com.manipur.khannnasiservice.entity.Article

interface ArticleService {
    fun getAllArticles(): List<Article>
    fun getArticleById(articleId: Long): Article?
    fun createArticle(article: Article): Article
    fun updateArticle(articleId: Long, updatedArticle: Article): Article?
    fun deleteArticle(articleId: Long)
}