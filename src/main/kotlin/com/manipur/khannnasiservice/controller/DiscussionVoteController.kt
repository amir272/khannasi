package com.manipur.khannnasiservice.controller

import com.manipur.khannnasiservice.entity.DiscussionVote
import com.manipur.khannnasiservice.service.DiscussionVoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/discussion-votes")
class DiscussionVoteController(@Autowired private val discussionVoteService: DiscussionVoteService) {

    @GetMapping
    fun getAllVotes(): List<DiscussionVote> = discussionVoteService.getAllVotes()

    @GetMapping("/{id}")
    fun getVoteById(@PathVariable id: Long): ResponseEntity<DiscussionVote> {
        val vote = discussionVoteService.getVoteById(id)
        return if (vote != null) {
            ResponseEntity.ok(vote)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createVote(@RequestBody vote: DiscussionVote): DiscussionVote = discussionVoteService.createVote(vote)

    @PutMapping("/{id}")
    fun updateVote(@PathVariable id: Long, @RequestBody vote: DiscussionVote): ResponseEntity<DiscussionVote> {
        val updatedVote = discussionVoteService.updateVote(id, vote)
        return if (updatedVote != null) {
            ResponseEntity.ok(updatedVote)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteVote(@PathVariable id: Long): ResponseEntity<Void> {
        discussionVoteService.deleteVote(id)
        return ResponseEntity.noContent().build()
    }
}