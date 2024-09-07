package com.manipur.khannnasiservice.controller

import com.manipur.khannnasiservice.entity.DiscussionComment
import com.manipur.khannnasiservice.service.DiscussionCommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/discussion-comments")
class DiscussionCommentController(@Autowired private val discussionCommentService: DiscussionCommentService) {

    @GetMapping
    fun getAllComments(): List<DiscussionComment> = discussionCommentService.getAllComments()

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: Long): ResponseEntity<DiscussionComment> {
        val comment = discussionCommentService.getCommentById(id)
        return if (comment != null) {
            ResponseEntity.ok(comment)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createComment(@RequestBody comment: DiscussionComment): DiscussionComment = discussionCommentService.createComment(comment)

    @PutMapping("/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody comment: DiscussionComment): ResponseEntity<DiscussionComment> {
        val updatedComment = discussionCommentService.updateComment(id, comment)
        return if (updatedComment != null) {
            ResponseEntity.ok(updatedComment)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<Void> {
        discussionCommentService.deleteComment(id)
        return ResponseEntity.noContent().build()
    }
}