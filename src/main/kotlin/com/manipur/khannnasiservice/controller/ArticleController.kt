package com.manipur.khannnasiservice.controller

import com.manipur.khannnasiservice.entity.Article
import com.manipur.khannnasiservice.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/articles")
class ArticleController(@Autowired private val articleService: ArticleService) {

    @GetMapping
    fun getAllArticles(): List<Article> = articleService.getAllArticles()

    @GetMapping("/{id}")
    fun getArticleById(@PathVariable id: Long): ResponseEntity<Article> {
        val article = articleService.getArticleById(id)
        return if (article != null) {
            ResponseEntity.ok(article)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createArticle(@RequestBody article: Article): Article = articleService.createArticle(article)

    @PutMapping("/{id}")
    fun updateArticle(@PathVariable id: Long, @RequestBody article: Article): ResponseEntity<Article> {
        val updatedArticle = articleService.updateArticle(id, article)
        return if (updatedArticle != null) {
            ResponseEntity.ok(updatedArticle)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<Void> {
        articleService.deleteArticle(id)
        return ResponseEntity.noContent().build()
    }
}