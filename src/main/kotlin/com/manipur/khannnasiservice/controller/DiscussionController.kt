package com.manipur.khannnasiservice.controller

import com.manipur.khannnasiservice.entity.Discussion
import com.manipur.khannnasiservice.service.DiscussionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/discussions")
class DiscussionController(@Autowired private val discussionService: DiscussionService) {

    @GetMapping
    fun getAllDiscussions(): List<Discussion> = discussionService.getAllDiscussions()

    @GetMapping("/{id}")
    fun getDiscussionById(@PathVariable id: Long): ResponseEntity<Discussion> {
        val discussion = discussionService.getDiscussionById(id)
        return if (discussion != null) {
            ResponseEntity.ok(discussion)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createDiscussion(@RequestBody discussion: Discussion): Discussion = discussionService.createDiscussion(discussion)

    @PutMapping("/{id}")
    fun updateDiscussion(@PathVariable id: Long, @RequestBody discussion: Discussion): ResponseEntity<Discussion> {
        val updatedDiscussion = discussionService.updateDiscussion(id, discussion)
        return if (updatedDiscussion != null) {
            ResponseEntity.ok(updatedDiscussion)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteDiscussion(@PathVariable id: Long): ResponseEntity<Void> {
        discussionService.deleteDiscussion(id)
        return ResponseEntity.noContent().build()
    }
}