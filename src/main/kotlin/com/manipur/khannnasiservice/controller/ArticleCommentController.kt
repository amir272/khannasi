package com.manipur.khannnasiservice.controller

import com.manipur.khannnasiservice.entity.ArticleComment
import com.manipur.khannnasiservice.service.ArticleCommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comments")
class ArticleCommentController(@Autowired private val articleCommentService: ArticleCommentService) {

    @GetMapping
    fun getAllComments(): List<ArticleComment> = articleCommentService.getAllComments()

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: Long): ResponseEntity<ArticleComment> {
        val comment = articleCommentService.getCommentById(id)
        return if (comment != null) {
            ResponseEntity.ok(comment)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createComment(@RequestBody comment: ArticleComment): ArticleComment = articleCommentService.createComment(comment)

    @PutMapping("/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody comment: ArticleComment): ResponseEntity<ArticleComment> {
        val updatedComment = articleCommentService.updateComment(id, comment)
        return if (updatedComment != null) {
            ResponseEntity.ok(updatedComment)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<Void> {
        articleCommentService.deleteComment(id)
        return ResponseEntity.noContent().build()
    }
}